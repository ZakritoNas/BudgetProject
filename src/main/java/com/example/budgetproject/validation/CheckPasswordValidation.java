package com.example.budgetproject.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidation.class)
public @interface CheckPasswordValidation {

    String message() default "Your password does not meet security requirements";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
