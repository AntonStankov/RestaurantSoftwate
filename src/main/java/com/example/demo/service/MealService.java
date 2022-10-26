package com.example.demo.service;

import com.example.demo.dto.MealDto;
import com.example.demo.entity.Meal;

import java.util.List;

public interface MealService {
    Boolean saveMeal(Meal entity);
    Boolean registerMeal(MealDto dto);
    Boolean removeMeal(Long id);
    Boolean editMeal(String name);

    Boolean editAvailability(Meal entity);

    Meal findById(Long id);
//    ------------------------

    List<MealDto> getRestaurant1();
    List<MealDto> getRestaurant2();

}
