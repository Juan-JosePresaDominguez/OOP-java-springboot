package com.microcompany.productsservice.service;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @PersistenceContext
    EntityManager em;

    public List<Product> getProductsByText(String text) {
        return productsRepository.findByNameContaining(text);
    }

    // Excepción lanzada desde el Servicio (no desde el Controller)
    public List<Product> getAll(){
        List<Product> products = productsRepository.findAll();
        if (products != null && products.size() > 0) return products;
        else throw new ProductNotfoundException("La lista de productos está vacía");
    }

    public Product duplicate(Long id) {
        Product currProd = productsRepository.findById(id).get();
        em.detach(currProd);
//        Product newProduct = new Product(null, currProd.getName(), currProd.getSerial());
        currProd.setId(null);
        return productsRepository.save(currProd);
    }

}