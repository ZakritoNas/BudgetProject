package com.example.budgetproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExpensesCategoryDto {
    private String categoryName;
    private String color;
}
