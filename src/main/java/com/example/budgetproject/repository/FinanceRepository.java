package com.example.budgetproject.repository;

import com.example.budgetproject.domain.expenses.FinanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;


public interface FinanceRepository extends JpaRepository<FinanceEntity, Integer>, JpaSpecificationExecutor<FinanceEntity> {
    @Modifying
    void updateAmountById(Integer id, Double amount);

    @Modifying
    void deleteById(Integer id);
}

