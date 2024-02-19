package com.example.budgetproject.service.impl;

import com.example.budgetproject.domain.ExpensesEntity;
import com.example.budgetproject.dto.ExpensesDto;
import com.example.budgetproject.mapper.ExpensesMapper;
import com.example.budgetproject.repository.ExpensesRepository;
import com.example.budgetproject.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository repository;
    private final ExpensesMapper mapper;

    @Override
    public void save(ExpensesDto dto) {
        ExpensesEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    public void delete(ExpensesDto dto) {
        ExpensesEntity entity = mapper.toEntity(dto);
        repository.delete(entity);
    }

    @Override
    public List<ExpensesDto> findAll() {
        List<ExpensesEntity> all = repository.findAll();
        List<ExpensesDto> dtoList = mapper.toDto(all);
        return dtoList;
    }
}
