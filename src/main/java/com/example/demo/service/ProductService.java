package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductsDomain;

public interface ProductService {
    Boolean save(ProductsDomain entity);
    ProductsDomain addProduct(ProductDto dto);

    ProductsDomain findByName(String name);
}
