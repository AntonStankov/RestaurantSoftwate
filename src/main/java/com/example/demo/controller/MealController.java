package com.example.demo.controller;

import com.example.demo.dto.MealDto;
import com.example.demo.entity.Meal;
import com.example.demo.entity.UserDomain;
import com.example.demo.service.MealService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/meal")
public class MealController {

    @Autowired
    UserService userService;
    @Autowired
    MealService mealService;

    @PostMapping("/addMeal")
    public MealDto addMeal(@RequestBody MealDto dto, @RequestParam("image") MultipartFile image) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getType().toString().equals("SUPERADMIN")){
            mealService.registerMeal(dto);
            return dto;
        }
        else return null;
    }



    @PostMapping("/addImage")
    public Boolean addImage(@RequestParam("image") MultipartFile image, @RequestParam("id") Long id) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(id);
        if(user.getType().toString().equals("SUPERADMIN")){
            meal.setImage(image.getBytes());
        }
        return mealService.editAvailability(meal);


    }

    @PostMapping("/editAvailabilityInRestaurant1")
    public Boolean editAvailabilityInRestaurant1(@RequestBody Meal editMeal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(editMeal.getId());

        if(user.getType().toString().equals("ADMIN") && user.getRestaurant().toString().equals("Restaurant1") || user.getType().toString().equals("SUPERADMIN")){
            meal.setAvailabilityInRestaurant1(editMeal.getAvailabilityInRestaurant1());
        }
        else throw new RuntimeException("You don't have permissions!");

        return mealService.editAvailability(meal);
    }


    @PostMapping("/editAvailabilityInRestaurant2")
    public Boolean editAvailabilityInRestaurant2(@RequestBody Meal editMeal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(editMeal.getId());


        if(user.getType().toString().equals("ADMIN") && user.getRestaurant().toString().equals("Restaurant2") || user.getType().toString().equals("SUPERADMIN")){
            meal.setAvailabilityInRestaurant2(editMeal.getAvailabilityInRestaurant2());
        }
        else throw new RuntimeException("You don't have permissions!");

        return mealService.editAvailability(meal);
    }


    @PostMapping("/deleteMeal")
    public Boolean deleteMeal(@RequestBody MealDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getType().toString().equals("SUPERADMIN")){
            return mealService.removeMeal(dto.getId());
        }
        else return false;
    }


//    ---------------------EDITING----------------------------------------------


    @PostMapping("/editPrice")
    public Boolean editPrice(@RequestBody Meal editMeal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(editMeal.getId());

        if(user.getType().toString().equals("SUPERADMIN")){
            meal.setPrice(editMeal.getPrice());
        }
        else throw new RuntimeException("You don't have permissions!");

        return mealService.editAvailability(meal);
    }


    @PostMapping("/editCategory")
    public Boolean editCategory(@RequestBody Meal editMeal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(editMeal.getId());

        if(user.getType().toString().equals("SUPERADMIN")){
            meal.setCategory(editMeal.getCategory());
        }
        else throw new RuntimeException("You don't have permissions!");

        return mealService.editAvailability(meal);
    }


    @PostMapping("/editProducts")
    public Boolean editProducts(@RequestBody Meal editMeal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(editMeal.getId());

        if(user.getType().toString().equals("SUPERADMIN")){
            meal.setProducts(editMeal.getProducts());
        }
        else throw new RuntimeException("You don't have permissions!");

        return mealService.editAvailability(meal);
    }

    @PostMapping("/editName")
    public Boolean editName(@RequestBody Meal editMeal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(editMeal.getId());

        if(user.getType().toString().equals("SUPERADMIN")){
            meal.setName(editMeal.getName());
        }
        else throw new RuntimeException("You don't have permissions!");

        return mealService.editAvailability(meal);
    }

    @GetMapping("/getRestaurant1")
    public List<MealDto> getAvailable1(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getRestaurant().toString().equals("Restaurant1")){
            return mealService.getRestaurant1();
        }
        else throw new RuntimeException("You are Restaurant1 client");
    }

    @GetMapping("/getRestaurant2")
    public List<MealDto> getAvailable2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getRestaurant().toString().equals("Restaurant2")){
            return mealService.getRestaurant1();
        }
        else throw new RuntimeException("You are Restaurant2 client");
    }

    @PostMapping("/editWeight")
    public Boolean editWeight(@RequestBody Meal editMeal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        Meal meal = mealService.findById(editMeal.getId());

        if(user.getType().toString().equals("SUPERADMIN")){
            meal.setWeight(editMeal.getWeight());
        }
        else throw new RuntimeException("You don't have permissions!");

        return mealService.editAvailability(meal);
    }
}
