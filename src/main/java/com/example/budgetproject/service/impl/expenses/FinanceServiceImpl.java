package com.example.budgetproject.service.impl.expenses;

import com.example.budgetproject.domain.expenses.FinanceCategoryEntity;
import com.example.budgetproject.domain.expenses.FinanceEntity;
import com.example.budgetproject.dto.expenses.FinanceDto;
import com.example.budgetproject.dto.expenses.FinanceSearchDto;
import com.example.budgetproject.mapper.FinanceMapper;
import com.example.budgetproject.repository.FinanceRepository;
import com.example.budgetproject.service.FinanceService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository repository;
    @Qualifier("financeMapper")
    private final FinanceMapper mapper;

    @Override
    public void save(FinanceDto dto) {
        FinanceEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete (Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<FinanceEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void updateAmount(Integer id, Double amount) {
            repository.updateAmountById(id, amount);
    }

    @Override
    public Double sum() {
        List<FinanceEntity> all = repository.findAll();
        if (all != null || !all.isEmpty()){
        Double sum = all.stream()
                .collect(Collectors.summingDouble(FinanceEntity::getAmount));
        return sum;
        }
        return 0.00;
    }

    @Override
    public Double sumOfGain() {
        List<FinanceEntity> all = repository.findAll();
        if (all != null || !all.isEmpty()){
            Double sum = all.stream()
                    .filter(p -> p.getAmount() > 0)
                    .collect(Collectors.summingDouble(FinanceEntity::getAmount));
            return sum;
        }
        return 0.00;
    }

    @Override
    public Double sumOfExpenses() {
        List<FinanceEntity> all = repository.findAll();
        if (all != null || !all.isEmpty()){
            Double sum = all.stream()
                    .filter(p -> p.getAmount() < 0)
                    .collect(Collectors.summingDouble(FinanceEntity::getAmount));
            return sum;
        }
        return 0.00;
    }

    @Override
    public List<FinanceEntity> search(FinanceSearchDto dto) {
        return repository.findAll(createSpecif(dto));
    }

    @Override
    public List<FinanceEntity> filterByMonth(LocalDate date) {
        List<FinanceEntity> all = repository.findAll();
        List<FinanceEntity> collect = all.stream().filter(exp -> exp.getDate().getMonth().equals(date.getMonth()))
                .collect(Collectors.toList());
        return collect;
    }

    private Specification<FinanceEntity> createSpecif(FinanceSearchDto searchDto) {
        return (root, query, builder) -> {
            LocalDate dateFrom = searchDto.getDateFrom();
            LocalDate dateTo = searchDto.getDateTo();
            Double amountFrom = searchDto.getAmountFrom();
            Double amountTo = searchDto.getAmountTo();
            FinanceCategoryEntity category = searchDto.getCategory();

            var predicates = new ArrayList<Predicate>();

            if (dateFrom != null) {
                Predicate dateGe = builder.equal(root.get("date"), dateFrom);
                predicates.add(dateGe);
            }
            if (dateTo != null) {
                Predicate dateLe = builder.equal(root.get("date"), dateTo);
                predicates.add(dateLe);
            }
            if (amountFrom != null) {
                Predicate amountGe = builder.ge(root.get("amount"), amountFrom);
                predicates.add(amountGe);
            }
            if (amountTo != null) {
                Predicate amountLe = builder.le(root.get("amount"), amountTo);
                predicates.add(amountLe);
            }

            if (category != null) {
                Predicate category1 = builder.equal(root.get("category"), category);
                predicates.add(category1);
            }
            Predicate[] array = predicates.toArray(Predicate[]::new);
            return builder.and(array);
        };
    }
}
