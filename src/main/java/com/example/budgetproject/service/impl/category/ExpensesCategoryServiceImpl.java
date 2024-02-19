package com.example.budgetproject.service.impl.expenses;

import com.example.budgetproject.domain.expenses.ExpensesCategoryEntity;
import com.example.budgetproject.dto.expenses.ExpensesCategoryDto;
import com.example.budgetproject.mapper.ExpensesCategoryMapper;
import com.example.budgetproject.repository.ExpensesCategoryRepository;
import com.example.budgetproject.service.ExpensesCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ExpensesCategoryServiceImpl implements ExpensesCategoryService {

    private final ExpensesCategoryRepository repository;

    private final ExpensesCategoryMapper mapper;

    @Override
    public void save(ExpensesCategoryDto dto) {
        ExpensesCategoryEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    public void delete(ExpensesCategoryDto dto) {
        ExpensesCategoryEntity entity = mapper.toEntity(dto);
        repository.delete(entity);
    }

    @Override
    public List<ExpensesCategoryDto> findAll() {
        List<ExpensesCategoryEntity> all = repository.findAll();
        List<ExpensesCategoryDto> dtoList = mapper.toDto(all);
        return dtoList;
    }
}
