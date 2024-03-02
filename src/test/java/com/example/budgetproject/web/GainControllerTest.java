package com.example.budgetproject.web;

import com.example.budgetproject.domain.expenses.FinanceEntity;
import com.example.budgetproject.dto.expenses.FinanceDto;
import com.example.budgetproject.dto.expenses.FinanceSearchDto;
import com.example.budgetproject.repository.FinanceRepository;
import com.example.budgetproject.service.impl.expenses.FinanceServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WebMvcTest(GainController.class)
class GainControllerTest {


    @Autowired
    private FinanceServiceImpl service;

    @Autowired
    private FinanceRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void findAll() throws Exception {
        FinanceEntity entity1 = new FinanceEntity();
        entity1.setAmount(458.00);
        FinanceEntity entity2 = new FinanceEntity();
        entity2.setAmount(69.00);
        repository.save(entity1);
        repository.save(entity2);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/findAllGain").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        FinanceDto[] entity = mapper.readValue(mvcResult.getResponse().getContentAsString(), FinanceDto[].class);

        Assertions.assertEquals(2, entity.length);
    }

    @Test
    void saveGain() throws Exception {
        FinanceEntity entity1 = new FinanceEntity();
        entity1.setAmount(458.00);
        repository.save(entity1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/saveGain").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        FinanceDto entity = mapper.readValue(mvcResult.getResponse().getContentAsString(), FinanceDto.class);

        List<FinanceEntity> all = repository.findAll();

        Assertions.assertEquals(458.00, entity.getAmount());
        Assertions.assertEquals(1, all.size());
    }

    @Test
    void deleteGain() throws Exception {
        FinanceEntity entity1 = new FinanceEntity();
        entity1.setAmount(485.02);
        repository.save(entity1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/deleteGain").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        FinanceDto categoryDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), FinanceDto.class);

        List<FinanceEntity> all = repository.findAll();

        Assertions.assertEquals(0, all.size());
    }

    @Test
    void searchGains() throws Exception {
        FinanceEntity entity1 = new FinanceEntity();
        entity1.setAmount(458.00);
        FinanceEntity entity2 = new FinanceEntity();
        entity2.setAmount(69.00);
        repository.save(entity1);
        repository.save(entity2);
        FinanceSearchDto search = new FinanceSearchDto();
        search.setAmountTo(460.00);
        search.setAmountFrom(100.00);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/searchGain").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        FinanceSearchDto[] searchDtos = mapper.readValue(mvcResult.getResponse().getContentAsString(), FinanceSearchDto[].class);

        Assertions.assertEquals(1, searchDtos.length);
    }
}