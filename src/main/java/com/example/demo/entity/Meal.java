package com.example.demo.entity;

import com.example.demo.enums.MealCategory;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@NamedQueries({
        @NamedQuery(name = "Meal.findById", query = "SELECT j FROM Meal j WHERE j.id = :id"),
        @NamedQuery(name = "Meal.getRestaurant1", query = "SELECT u FROM Meal u WHERE u.availabilityInRestaurant1 = :availabilityInRestaurant1"),
        @NamedQuery(name = "Meal.getRestaurant2", query = "SELECT u FROM Meal u WHERE u.availabilityInRestaurant2 = :availabilityInRestaurant2")

})
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private MealCategory category;

    @Column(nullable = false)
    private int price;//

    @Column(nullable = false)
    private Boolean availabilityInRestaurant1;

    @Column(nullable = false)
    private Boolean availabilityInRestaurant2;

    @Column
//    @ElementCollection(targetClass=String.class)
    private String products;

    @Column
    private float weight;

    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] imageBytes;

}
