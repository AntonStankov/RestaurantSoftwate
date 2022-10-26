package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserDomain;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    public Boolean register(@RequestBody UserDto dto){
        return userService.register(dto);
    }

    @GetMapping("hello")
    public String hello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());
        return user.getType().toString();
    }
}
