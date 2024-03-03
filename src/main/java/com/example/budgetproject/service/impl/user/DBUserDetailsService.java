package com.example.budgetproject.service.impl.user;

import com.example.budgetproject.domain.UserEntity;
import com.example.budgetproject.dto.UserDto;
import com.example.budgetproject.mapper.UserMapper;
import com.example.budgetproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class DBUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    @Qualifier("userMapper")
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username).orElse(null);
    }

    public void save (UserDto dto){
        UserEntity entity = mapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        repository.save(entity);
    }

    public void delete (Integer id){
        repository.deleteById(id);
    }

    public List<UserDto> findAll() {
        List<UserEntity> all = repository.findAll();
        List<UserDto> dto = mapper.toDto(all);
        return dto;
    }

}
