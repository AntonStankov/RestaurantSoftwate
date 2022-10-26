package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserDomain;

public interface UserService {
    Boolean save(UserDomain entity);
    Boolean register(UserDto dto);
    Boolean existsByUsername(String username);
    UserDomain findByUsername(String username);
}
