package com.example.budgetproject.dto;

import com.example.budgetproject.domain.ExpensesCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesDto {
    private Float amount;
    private ExpensesCategoryEntity category;
}
