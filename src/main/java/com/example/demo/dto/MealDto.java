package com.example.demo.dto;


import com.example.demo.entity.ProductsDomain;
import com.example.demo.enums.MealCategory;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MealDto {
    private Long id;
    private String name;
    private MealCategory category;
    private int price;
    private Boolean availabilityInRestaurant1;
    private Boolean availabilityInRestaurant2;
    private String products1;
    private List<String> products;
    private float weight;
}
