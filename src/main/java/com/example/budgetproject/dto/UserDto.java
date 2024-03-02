package com.example.budgetproject.dto;

import com.example.budgetproject.validation.CheckUserNameValidation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDto {
    @CheckUserNameValidation
    private String userName;
    private String password;
}
