package com.example.demo.service.impl;

import com.example.demo.dto.MealDto;
import com.example.demo.entity.Meal;
import com.example.demo.mapper.Mapper;
import com.example.demo.service.MealRepository;
import com.example.demo.service.MealService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MealRepository mealRepository;

    @Autowired
    ProductService productService;

    @Override
    @Transactional
    public Boolean saveMeal(Meal entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return true;
    }

    @Override
    @Transactional
    public Boolean registerMeal(MealDto dto) {
        Meal meal = new Meal();
        meal.setName(dto.getName());
        meal.setCategory(dto.getCategory());
//        meal.setProducts(dto.getProducts());
        List<String> prd = new ArrayList<>();
        for(int i = 0; i < dto.getProducts().size(); i++){
            prd.add(productService.findByName(dto.getProducts().get(i)).getName());
        }
        String finalProducts = null;
        for(int i = 0; i < prd.size(); i++){
            finalProducts += prd.get(i) + ", ";
        }
        finalProducts = finalProducts.substring(4, finalProducts.length() - 2);
        meal.setProducts(finalProducts);
        meal.setPrice(dto.getPrice());
        meal.setAvailabilityInRestaurant1(dto.getAvailabilityInRestaurant1());
        meal.setAvailabilityInRestaurant2(dto.getAvailabilityInRestaurant2());
        meal.setWeight(dto.getWeight());
        meal.setImage(dto.getImage());
        return saveMeal(meal);
    }

    @Override
    public Boolean removeMeal(Long id) {
        mealRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean editMeal(String name) {
        return null;
    }

    @Override
    @Transactional
    public Boolean editAvailability(Meal entity) {
        Meal updated = entityManager.merge(entity);
        entityManager.flush();
        return true;
    }



    @Override
    public Meal findById(Long id) {
        Query query = entityManager.createNamedQuery("Meal.findById").setParameter("id", id);
        List<Meal> meals = query.getResultList();
        if(meals.isEmpty()){
            return null;
        }else{
            return meals.get(0);
        }
    }

    @Override
    public List<MealDto> getRestaurant1() {
        Query query = entityManager.createNamedQuery("Meal.getRestaurant1").setParameter("availabilityInRestaurant1", true);
        List<Meal> meals = query.getResultList();
        List<MealDto> dtos = new ArrayList<>();
        for (Meal meal : meals){
            dtos.add(Mapper.toMealDto(meal));
        }

        return dtos;
    }

    @Override
    public List<MealDto> getRestaurant2() {
        Query query = entityManager.createNamedQuery("Meal.getRestaurant2").setParameter("availabilityInRestaurant2", true);
        List<Meal> meals = query.getResultList();
        List<MealDto> dtos = new ArrayList<>();
        for (Meal meal : meals){
            dtos.add(Mapper.toMealDto(meal));
        }

        return dtos;
    }
}
