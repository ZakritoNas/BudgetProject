package com.example.budgetproject.dto.expenses;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceSearchDto {
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private Double amountFrom;
        private Double amountTo;
        private FinanceCategoryEntity category;
}
