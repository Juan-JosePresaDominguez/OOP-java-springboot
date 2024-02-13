package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    @Value("${datos.nombre:Sin nombre}")
    //@Value("${datos.nombre}")
    String nombre;

    // Ejecutando curl http://localhost:8080/hola -v
    @RequestMapping("/hola")
    public String hello() {
        //return "Hola Mundo!";
        return "Hola " + nombre + "!";
    }

    // Ejecutando curl http://localhost:8080/quetal -v
    @RequestMapping("/quetal")
    public String whatsup() {
        return "Qué tal?";
    }
}
