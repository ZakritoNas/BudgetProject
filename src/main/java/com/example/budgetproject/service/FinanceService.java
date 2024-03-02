package com.example.budgetproject.service;

import com.example.budgetproject.domain.expenses.FinanceEntity;
import com.example.budgetproject.dto.expenses.FinanceDto;
import com.example.budgetproject.dto.expenses.FinanceSearchDto;

import java.time.LocalDate;
import java.util.List;

public interface FinanceService {
    void save (FinanceDto dto);
    void delete (Integer id);
    List<FinanceEntity> findAll ();
    void updateAmount(Integer id, Double amount);
    Double sum();
    Double sumOfGain();
    Double sumOfExpenses();
    List<FinanceEntity> search (FinanceSearchDto dto);
    List<FinanceEntity> filterByMonth (LocalDate date);
}
