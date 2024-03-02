package com.example.budgetproject.service.impl.category;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import com.example.budgetproject.dto.expenses.FinanceCategoryDto;
import com.example.budgetproject.mapper.FinanceCategoryMapper;
import com.example.budgetproject.repository.FinanceCategoryRepository;
import com.example.budgetproject.service.FinanceCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class FinanceCategoryServiceImpl implements FinanceCategoryService {

    private final FinanceCategoryRepository repository;
    @Qualifier("financeCategoryMapper")
    private final FinanceCategoryMapper mapper;

    @Override
    public void save (FinanceCategoryDto dto) {
        if (dto == null){
            throw new RuntimeException("you can't save empty object");
        }
        FinanceCategoryEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<FinanceCategoryEntity> findAll() {
       return repository.findAll();
    }
}
