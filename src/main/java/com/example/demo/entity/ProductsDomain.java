package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@NamedQuery(name = "ProductsDomain.findByName", query = "SELECT j FROM ProductsDomain j WHERE j.name = :name")

public class ProductsDomain {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
}
