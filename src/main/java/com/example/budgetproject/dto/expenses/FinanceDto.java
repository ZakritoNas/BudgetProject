package com.example.budgetproject.dto.expenses;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceDto {
    private LocalDate date = LocalDate.now();
    private Double amount;
    private FinanceCategoryEntity category;

}
