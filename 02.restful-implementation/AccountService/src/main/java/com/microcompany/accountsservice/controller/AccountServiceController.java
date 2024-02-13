package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.persistence.AccountRepository;
import com.microcompany.accountsservice.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Crear una aplicación AccountService que nos permita exponer una API para gestionar cuentas de usuario */
@RestController
@RequestMapping("/accounts")
public class AccountServiceController {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

    @Autowired
    private AccountService service;

    @Autowired
    private AccountRepository repo;

    // Métodos POST Equivalentes SERVICIO vs. REPOSITORIO
    // NOTA: Dejar siempre uno de ellos comentado. De lo contrario se producirá el error 'to {POST [/accounts]}: There is already 'accountServiceController' bean method' y no se levantá el servidor.
    // Método POST (Crear Cuenta 'create' - SERVICIO)
    @PostMapping("")
    public ResponseEntity<Account> create(@RequestBody Account newAccount) {
        return new ResponseEntity<>(service.create(newAccount), HttpStatus.CREATED);
    }
    // Método POST (Crear Cuenta 'save' - REPOSITORIO)
    /*@PostMapping("")
    public ResponseEntity<Account> save(@RequestBody Account newAccount) {
        logger.info("newAccount:" + newAccount);
        return new ResponseEntity<>(repo.save(newAccount), HttpStatus.CREATED);
    }*/

    // Método GET (Obtener Cuentas 'getAccounts' - SERVICIO)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return service.getAccounts();
        //return repo.findAll();
    }

    // Método GET (Obtener Cuenta por ID 'getAccount' - SERVICIO)
    @GetMapping("/{aid}")
    public Account getAccount(@PathVariable("aid") Long id) {
        return service.getAccount(id);
    }

    // Método GET (Obtener Cuenta por Owner 'getAccountByOwnerId' - SERVICIO)
    @GetMapping("/{oid}")
    public List<Account> getAccountByOwnerId(@PathVariable("oid") Long ownerId) {
        return service.getAccountByOwnerId(ownerId);
        //return repo.findByOwnerId(1L);
    }





}
