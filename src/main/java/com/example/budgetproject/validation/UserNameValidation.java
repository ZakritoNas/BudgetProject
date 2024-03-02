package com.example.budgetproject.validation;

import com.example.budgetproject.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserNameValidation implements ConstraintValidator<CheckUserNameValidation, String> {

    private final UserRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()){
            return false;
        }

        if (repository.findAll().contains(value)){
            return false;
        }
        return true;
    }
}
