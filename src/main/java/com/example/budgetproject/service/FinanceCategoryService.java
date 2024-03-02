package com.example.budgetproject.service;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import com.example.budgetproject.dto.expenses.FinanceCategoryDto;

import java.util.List;

public interface FinanceCategoryService {
    void save (FinanceCategoryDto dto);
    void delete (Integer id);
    List<FinanceCategoryEntity> findAll ();

}
