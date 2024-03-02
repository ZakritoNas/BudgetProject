package com.example.budgetproject.service.impl.category;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import com.example.budgetproject.dto.expenses.FinanceCategoryDto;
import com.example.budgetproject.mapper.FinanceCategoryMapperImpl;
import com.example.budgetproject.repository.FinanceCategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class FinanceCategoryServiceImplTest {

    FinanceCategoryMapperImpl mapper = new FinanceCategoryMapperImpl();

    @Autowired
    FinanceCategoryRepository repository;

    @Test
    void testSave() {
        //Given
        FinanceCategoryServiceImpl categoryService = new FinanceCategoryServiceImpl(repository, mapper);
        FinanceCategoryDto categoryDto = new FinanceCategoryDto();
        categoryDto.setCategoryName("Products");
        //When
        categoryService.save(categoryDto);

        List<FinanceCategoryEntity> all = categoryService.findAll();
        //Then
        Assertions.assertEquals(1, all.size());
    }

    @Test
    void testDelete() {
        FinanceCategoryServiceImpl categoryService = new FinanceCategoryServiceImpl(repository, mapper);
        FinanceCategoryDto categoryDto = new FinanceCategoryDto();
        categoryDto.setCategoryName("Products");

        categoryService.save(categoryDto);
        //When
        categoryService.delete(1);

        List<FinanceCategoryEntity> all = categoryService.findAll();
        //Then
        Assertions.assertEquals(0, all.size());
    }

    @Test
    void testFindAll() {
        //Given
        FinanceCategoryServiceImpl categoryService = new FinanceCategoryServiceImpl(repository, mapper);
        FinanceCategoryDto categoryDto1 = new FinanceCategoryDto();
        categoryDto1.setCategoryName("Products");
        FinanceCategoryDto categoryDto2 = new FinanceCategoryDto();
        categoryDto2.setCategoryName("Closes");
        FinanceCategoryDto categoryDto3 = new FinanceCategoryDto();
        categoryDto3.setCategoryName("Transport");
        //When
        categoryService.save(categoryDto1);
        categoryService.save(categoryDto2);
        categoryService.save(categoryDto3);

        List<FinanceCategoryEntity> all = categoryService.findAll();

        //Then
        Assertions.assertEquals(3, all.size());
    }
}