package com.example.budgetproject.domain.expenses;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "finance_categories")
@NamedQuery(name = "FinanceCategoryEntity.deleteById", query = "delete FinanceCategoryEntity where id=:id")
public class FinanceCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;

    @OneToMany (mappedBy = "category",
            cascade = CascadeType.REMOVE,
//            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<FinanceEntity> finances;

    public void addFinance (FinanceEntity finance){
        if(finances == null){
            finances = new ArrayList<>();
        }
        this.finances = finances;
        finance.setCategory(this);
    }

}
