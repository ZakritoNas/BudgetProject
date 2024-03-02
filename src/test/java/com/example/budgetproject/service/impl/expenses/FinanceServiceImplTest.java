package com.example.budgetproject.service.impl.expenses;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import com.example.budgetproject.domain.expenses.FinanceEntity;
import com.example.budgetproject.dto.expenses.FinanceCategoryDto;
import com.example.budgetproject.dto.expenses.FinanceDto;
import com.example.budgetproject.dto.expenses.FinanceSearchDto;
import com.example.budgetproject.mapper.FinanceMapperImpl;
import com.example.budgetproject.repository.FinanceRepository;
import com.example.budgetproject.service.impl.category.FinanceCategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class FinanceServiceImplTest {

    FinanceMapperImpl mapper = new FinanceMapperImpl();

    @Autowired
    private FinanceRepository repository;



    @Test
    void testSave() {
        //Given
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto = new FinanceDto();
        dto.setAmount(485.02);
        //When
        service.save(dto);

        List<FinanceEntity> all = service.findAll();
        //Then
        Assertions.assertEquals(1, all.size());
    }

    @Test
    void delete() {
        //Given
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto = new FinanceDto();
        dto.setAmount(485.02);
        //When
        service.delete(1);
        List<FinanceEntity> all = service.findAll();

        //Then
        Assertions.assertEquals(0, all.size());
    }

    @Test
    void findAll() {
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto1 = new FinanceDto();
        dto1.setAmount(485.02);
        FinanceDto dto2 = new FinanceDto();
        dto2.setAmount(-856.02);
        FinanceDto dto3 = new FinanceDto();
        dto3.setAmount(1005.02);
        //When
        service.save(dto1);
        service.save(dto2);
        service.save(dto3);

        List<FinanceEntity> all = service.findAll();

        //Then
        Assertions.assertEquals(3, all.size());
    }

//    @Test
//    void updateAmount() {
//        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
//        FinanceDto dto = new FinanceDto();
//        dto.setAmount(485.02);
//        //When
//        service.save(dto);
//        service.updateAmount(1, 805.04);
//        var byId = repository.findById(1);
//        //Then
//        Assertions.assertEquals(805.04, dto.getAmount());
//    }

    @Test
    void sum() {
        //Given
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto1 = new FinanceDto();
        dto1.setAmount(485.00);
        FinanceDto dto2 = new FinanceDto();
        dto2.setAmount(-452.00);
        FinanceDto dto3 = new FinanceDto();
        dto3.setAmount(22.00);
        //When
        service.save(dto1);
        service.save(dto2);
        service.save(dto3);

        Double sum = service.sum();

        //Then
        Assertions.assertEquals(55.00, sum);
    }

    @Test
    void sumOfGain() {
        //Given
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto1 = new FinanceDto();
        dto1.setAmount(485.00);
        FinanceDto dto2 = new FinanceDto();
        dto2.setAmount(-452.00);
        FinanceDto dto3 = new FinanceDto();
        dto3.setAmount(22.00);
        //When
        service.save(dto1);
        service.save(dto2);
        service.save(dto3);

        Double sum = service.sumOfGain();

        //Then
        Assertions.assertEquals(507.00, sum);
    }

    @Test
    void sumOfExpenses() {
        //Given
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto1 = new FinanceDto();
        dto1.setAmount(485.00);
        FinanceDto dto2 = new FinanceDto();
        dto2.setAmount(-452.00);
        FinanceDto dto3 = new FinanceDto();
        dto3.setAmount(22.00);
        FinanceDto dto4 = new FinanceDto();
        dto4.setAmount(-245.00);
        //When
        service.save(dto1);
        service.save(dto2);
        service.save(dto3);
        service.save(dto4);

        Double sum = service.sumOfExpenses();

        //Then
        Assertions.assertEquals(-697.00, sum);
    }

    @Test
    void search() {
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto1 = new FinanceDto();
        dto1.setAmount(485.00);
        FinanceDto dto2 = new FinanceDto();
        dto2.setAmount(-452.00);
        FinanceDto dto3 = new FinanceDto();
        dto3.setAmount(22.00);
        FinanceSearchDto search = new FinanceSearchDto();

        service.save(dto1);
        service.save(dto2);
        service.save(dto3);
        search.setAmountFrom(100.00);
        search.setAmountTo(500.00);

        List<FinanceEntity> search1 = service.search(search);

        Assertions.assertEquals(485.00, search1.get(0).getAmount());

    }

    @Test
    void filterByMonth() {
        FinanceServiceImpl service = new FinanceServiceImpl(repository, mapper);
        FinanceDto dto1 = new FinanceDto();
        dto1.setDate(LocalDate.of(2024, 1, 12));
        FinanceDto dto2 = new FinanceDto();
        dto2.setDate(LocalDate.of(2024, 3, 1));
        FinanceDto dto3 = new FinanceDto();
        dto1.setDate(LocalDate.of(2024, 1, 25));
        FinanceSearchDto search = new FinanceSearchDto();

        service.save(dto1);
        service.save(dto2);
        service.save(dto3);

        List<FinanceEntity> financeEntities = service.filterByMonth(LocalDate.now());
        LocalDate date = financeEntities.get(1).getDate();

        Assertions.assertEquals(dto2.getDate().getMonth(), date.getMonth());
    }
}