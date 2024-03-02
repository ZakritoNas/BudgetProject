package com.example.budgetproject.dto.expenses;

import com.example.budgetproject.domain.enams.CategoryColor;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class FinanceCategoryDto {
    private String categoryName;
}
