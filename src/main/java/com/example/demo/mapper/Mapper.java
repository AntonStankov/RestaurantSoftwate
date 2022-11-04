package com.example.demo.mapper;

import com.example.demo.dto.MealDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Meal;
import com.example.demo.entity.UserDomain;

public class Mapper {

    public static UserDto toDto(UserDomain entity) {
        if (entity == null) {
            return null;
        }
        return new UserDto(entity.getUsername(), null, entity.getType(), entity.getRestaurant());
    }

    public static UserDomain toUser(UserDto dto) {
        if (dto == null) {
            return null;
        }
        return new UserDomain(null, dto.getUsername(), null, dto.getType(), dto.getRestaurant());
    }

    public static MealDto toMealDto(Meal meal){
        if(meal == null){
            return null;
        }
        return new MealDto(meal.getId(), meal.getName(), meal.getCategory(), meal.getPrice(),meal.getAvailabilityInRestaurant1(), meal.getAvailabilityInRestaurant2(), meal.getProducts(), null, meal.getWeight(), meal.getImage());
    }

    public static Meal toMealDomain(MealDto dto){
        if(dto == null){
            return null;
        }
        return new Meal(null, dto.getName(), dto.getCategory(), dto.getPrice(), dto.getAvailabilityInRestaurant1(), dto.getAvailabilityInRestaurant2(),dto.getProducts1(), dto.getWeight(), dto.getImage());
    }
}
