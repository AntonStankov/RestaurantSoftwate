package com.example.demo.dto;

import com.example.demo.enums.RestaurantsEnum;
import com.example.demo.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private UserTypeEnum type;
    private RestaurantsEnum restaurant;

}
