package com.example.budgetproject.mapper;

import com.example.budgetproject.domain.expenses.FinanceEntity;
import com.example.budgetproject.dto.expenses.FinanceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
@Component
public interface FinanceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "date", source = "date")
    FinanceEntity toEntity (FinanceDto dto);

    @Mapping(target = "category", source = "category")
    @Mapping(target = "amount", source = "amount")
    FinanceDto toDto (FinanceEntity entity);

    List<FinanceDto> toDto (List<FinanceEntity> entityList);

}
