package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.persistence.AccountRepository;
import com.microcompany.accountsservice.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* Crear una aplicación AccountService que nos permita exponer una API para gestionar cuentas de usuario */
@RestController
@RequestMapping("/accounts")
public class AccountServiceController {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

    @Autowired
    private AccountService service;

    @Autowired
    private AccountRepository repo;

    // Método POST (Crear Cuenta - SERVICIO)
    @PostMapping("")
    public ResponseEntity<Account> create(@RequestBody Account newAccount) {
        return new ResponseEntity<>(service.create(newAccount), HttpStatus.CREATED);
    }

    // Método POST (Crear Cuenta - REPOSITORIO)
//    @PostMapping("")
//    public ResponseEntity<Account> save(@RequestBody Account newAccount) {
//        logger.info("newAccount:" + newAccount);
//        return new ResponseEntity<>(repo.save(newAccount), HttpStatus.CREATED);
//    }

}
