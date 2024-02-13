package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    private ProductsService service;

    @Autowired
    private ProductsRepository repo;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> getAll() {
        //return service.getProductsByText("");
        return repo.findAll();
    }
    
//    @RequestMapping("") // Para todas la peticiones que llegan a producto, va a concatenar la ruta del controller + "" ó "/"
//    public String get() {
//        return "Lista productos";
//    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<Product> getAll() {
//        return List.of(
//                new Product(1L, "estropajo", "cod-1"),
//                new Product(2L, "bayeta", "cod-2")
//        );
//    }

}