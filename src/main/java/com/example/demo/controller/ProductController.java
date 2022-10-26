package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductsDomain;
import com.example.demo.entity.UserDomain;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ProductsDomain addProduct(@RequestBody ProductDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getType().toString().equals("SUPERADMIN")){
            return productService.addProduct(dto);
        }
        else return null;
    }
}
