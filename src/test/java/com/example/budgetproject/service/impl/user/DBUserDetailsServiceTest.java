package com.example.budgetproject.service.impl.user;

import com.example.budgetproject.dto.UserDto;
import com.example.budgetproject.mapper.UserMapper;
import com.example.budgetproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DBUserDetailsServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;

    @Qualifier("userMapperImpl")
    @Autowired
    UserMapper mapper;

    @Test
    void save() {
        DBUserDetailsService service = new DBUserDetailsService(passwordEncoder, repository, mapper);

        UserDto dto = new UserDto();
        dto.setUserName("user1");
        dto.setPassword("pass1");

        service.save(dto);

        List<UserDto> all = service.findAll();
        Assertions.assertEquals(1, all.size());

    }

    @Test
    void delete() {
        DBUserDetailsService service = new DBUserDetailsService(passwordEncoder, repository, mapper);

        UserDto dto1 = new UserDto();
        dto1.setUserName("user1");
        dto1.setPassword("pass1");

        UserDto dto2 = new UserDto();
        dto2.setUserName("user2");
        dto2.setPassword("pass2");

        service.save(dto1);
        service.save(dto2);
        service.delete(1);

        List<UserDto> all = service.findAll();
        Assertions.assertEquals(1, all.size());
    }

    @Test
    void findAll() {
        DBUserDetailsService service = new DBUserDetailsService(passwordEncoder, repository, mapper);

        UserDto dto1 = new UserDto();
        dto1.setUserName("user1");
        dto1.setPassword("pass1");

        UserDto dto2 = new UserDto();
        dto2.setUserName("user2");
        dto2.setPassword("pass2");

        service.save(dto1);
        service.save(dto2);

        List<UserDto> all = service.findAll();
        Assertions.assertEquals(2, all.size());
    }
}