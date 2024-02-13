package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		logger.debug("Esto es un log de debug");
		logger.info("Esto es un log de info");
		logger.error("Esto es un log de error");
	}

//	@RequestMapping("/hola")
//	public String hello() {
//		return "Hola Mundo!";
//	}
//
//	@RequestMapping("/quetal")
//	public String whatsup() {
//		return "Qué tal?";
//	}

}
