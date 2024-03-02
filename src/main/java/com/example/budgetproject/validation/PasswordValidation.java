package com.example.budgetproject.validation;

import com.example.budgetproject.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class PasswordValidation implements ConstraintValidator<CheckPasswordValidation, String> {

    private final UserRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()){
            return false;
        }

        if (value.length() < 8){
            return false;
        }
        if (value.equals(value.toLowerCase()) || value.equals(value.toUpperCase())){
            return false;
        }
        return true;
    }
}
