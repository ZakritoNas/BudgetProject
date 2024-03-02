package com.example.budgetproject.web;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import com.example.budgetproject.dto.expenses.FinanceCategoryDto;
import com.example.budgetproject.repository.FinanceCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private FinanceCategoryRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void findAll() throws Exception {
        //Given
        FinanceCategoryEntity entity1 = new FinanceCategoryEntity();
        entity1.setCategoryName("Products");
        FinanceCategoryEntity entity2 = new FinanceCategoryEntity();
        entity2.setCategoryName("Products");
        repository.save(entity1);
        repository.save(entity2);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/findAllCategories").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        FinanceCategoryDto[] categoryDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), FinanceCategoryDto[].class);

        Assertions.assertEquals(2, categoryDto.length);

    }

    @Test
    void testSaveCategory() throws Exception {
        FinanceCategoryEntity dto1 = new FinanceCategoryEntity();
        dto1.setCategoryName("Products");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/saveCategory").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        FinanceCategoryDto categoryDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), FinanceCategoryDto.class);

        List<FinanceCategoryEntity> all = repository.findAll();

        Assertions.assertEquals("Products", categoryDto.getCategoryName());
        Assertions.assertEquals(1, all.size());

    }

    @Test
    void deleteCategory() throws Exception {
        FinanceCategoryEntity entity1 = new FinanceCategoryEntity();
        entity1.setCategoryName("Products");

        repository.save(entity1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/deleteCategory").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        FinanceCategoryDto categoryDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), FinanceCategoryDto.class);

        List<FinanceCategoryEntity> all = repository.findAll();

        Assertions.assertEquals(0, all.size());
    }
}