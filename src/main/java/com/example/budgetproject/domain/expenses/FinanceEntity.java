package com.example.budgetproject.domain.expenses;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@NamedQuery(name = "FinanceEntity.updateAmountById", query = "update FinanceEntity set amount = :amount where id=:id")
@NamedQuery(name = "FinanceEntity.deleteById", query = "delete FinanceEntity where id=:id")
@Table(name = "finance")
public class FinanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FinanceCategoryEntity category;
}
