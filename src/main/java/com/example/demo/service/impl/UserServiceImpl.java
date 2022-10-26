package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserDomain;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    EntityManager entityManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional //session with database (required)
    public Boolean save(UserDomain entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return true;
    }

    @Override
    @Transactional
    public Boolean register(UserDto dto) {
        if(existsByUsername(dto.getUsername())){
            throw new RuntimeException("Username already exist: " + dto.getUsername());
        }

        UserDomain userDomain = new UserDomain();
        userDomain.setUsername(dto.getUsername());
        userDomain.setPassword(passwordEncoder().encode(dto.getPassword()));
        userDomain.setType(dto.getType());
        userDomain.setRestaurant(dto.getRestaurant());
        return save(userDomain);
    }

    @Override
    public Boolean existsByUsername(String username) {
        Query query = entityManager.createNamedQuery("UserDomain.findByUsername").setParameter("username", username);
        List<UserDomain> users = query.getResultList();
        return !users.isEmpty();
    }

    @Override
    public UserDomain findByUsername(String username) {
        Query query = entityManager.createNamedQuery("UserDomain.findByUsername").setParameter("username", username);
        List<UserDomain> users = query.getResultList();
        if(users.isEmpty()){
            return null;
        }else{
            return users.get(0);
        }
    }
}
