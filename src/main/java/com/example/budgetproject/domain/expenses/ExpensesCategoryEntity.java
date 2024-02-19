package com.example.budgetproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "expeses_categories")
public class ExpensesCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String color;

    @OneToMany (mappedBy = "expenses")
    @ToString.Exclude
    private List<ExpensesEntity> expenses;
}
