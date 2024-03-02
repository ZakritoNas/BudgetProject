package com.example.budgetproject.mapper;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;

import com.example.budgetproject.domain.expenses.FinanceEntity;
import com.example.budgetproject.dto.expenses.FinanceCategoryDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
@Component
public interface FinanceCategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryName", source = "categoryName")
    @Mapping(target = "finances", ignore = true)
    FinanceCategoryEntity toEntity (FinanceCategoryDto dto);

    @AfterMapping
    default void addFinance (@MappingTarget FinanceCategoryEntity entity, FinanceCategoryDto dto){
        var finance = new FinanceEntity();
        finance.setName(dto.getCategoryName());
        entity.addFinance(finance);
    }

//    @AfterMapping
//    default void deleteExpenses (@MappingTarget FinanceCategoryEntity entity, FinanceCategoryDto dto){
//        var expenses = new FinanceEntity();
//        entity.deleteExpenses(expenses);
//    }
    @Mapping(target = "categoryName", source = "categoryName")
    FinanceCategoryDto toDto (FinanceCategoryEntity entity);

    List<FinanceCategoryDto> toDto (List<FinanceCategoryEntity> entityList);
}
