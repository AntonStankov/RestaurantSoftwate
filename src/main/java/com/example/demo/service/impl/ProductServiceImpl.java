package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductsDomain;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public Boolean save(ProductsDomain entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return true;
    }

    @Override
    @Transactional
    public ProductsDomain addProduct(ProductDto dto) {
        ProductsDomain product = new ProductsDomain();
        product.setName(dto.getName());
        save(product);
        return product;
    }

    @Override
    public ProductsDomain findByName(String name) {
        Query query = entityManager.createNamedQuery("ProductsDomain.findByName");
        query.setParameter("name", name);
        List<ProductsDomain> products = query.getResultList();

        if(products.isEmpty()){
            return null;
        }
        else return products.get(0);
    }
}
