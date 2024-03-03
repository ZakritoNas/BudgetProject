package com.example.budgetproject.mapper;

import com.example.budgetproject.domain.UserEntity;
import com.example.budgetproject.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
@Component
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "userName")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "perm", constant = "ROLE_USER")
    UserEntity toEntity(UserDto userDto);

    @Mapping(target = "userName", source = "login")
    UserDto toDto(UserEntity user);

    List<UserDto> toDto (List<UserEntity> entityList);
}
