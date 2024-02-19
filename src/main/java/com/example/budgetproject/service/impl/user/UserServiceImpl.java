package com.example.budgetproject.service.impl;

import com.example.budgetproject.domain.UserEntity;
import com.example.budgetproject.dto.UserDto;
import com.example.budgetproject.mapper.UserMapper;
import com.example.budgetproject.repository.UserRepository;
import com.example.budgetproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Qualifier("userMapper")
    private final UserMapper mapper;

    @Override
    public void delete(UserDto dto) {
        UserEntity entity = mapper.toEntity(dto);
        repository.delete(entity);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> all = repository.findAll();
        List<UserDto> dtoList = mapper.toDto(all);
        return dtoList;
    }
}
