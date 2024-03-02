package com.example.budgetproject.repository;
import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface FinanceCategoryRepository extends JpaRepository<FinanceCategoryEntity, Integer>{

    @Modifying
    void deleteById (Integer id);

}
