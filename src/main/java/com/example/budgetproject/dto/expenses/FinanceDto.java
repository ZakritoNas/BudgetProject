package com.example.budgetproject.dto.expenses;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import jakarta.validation.constraints.NotNull;
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
    @NotNull (message = "please enter amount")
    private Double amount;
    @NotNull(message = "please enter category")
    private FinanceCategoryEntity category;

}
