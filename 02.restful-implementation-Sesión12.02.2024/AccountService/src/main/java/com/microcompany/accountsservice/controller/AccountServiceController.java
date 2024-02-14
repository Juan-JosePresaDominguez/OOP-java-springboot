package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.exception.GlobalException;
import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.payload.ApiResponse;
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
        return new ResponseEntity<>(service.create(newAccount), HttpStatus.CREATED); // HTTP 201 Created
    }
    // Método POST (Crear Cuenta 'save' - REPOSITORIO)
    /*@PostMapping("")
    public ResponseEntity<Account> save(@RequestBody Account newAccount) {
        logger.info("newAccount:" + newAccount);
        return new ResponseEntity<>(repo.save(newAccount), HttpStatus.CREATED);
    }*/

    // Método GET (Obtener Cuentas 'getAccounts' - SERVICIO)
    @RequestMapping(value = "", method = RequestMethod.GET)
    /*public List<Account> getAccounts() {
        return service.getAccounts();
        //return repo.findAll();
    }*/
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(service.getAccounts(), HttpStatus.OK);
    }

    // Método GET (Obtener Cuenta por ID 'getAccount' - SERVICIO)
    @GetMapping("/{aid}")
    /*public Account getAccount(@PathVariable("aid") Long id) {
        return service.getAccount(id); // HTTP 200
    }*/
    public ResponseEntity<Account> getAccount(@PathVariable("aid") Long id) {
        return new ResponseEntity<>(service.getAccount(id), HttpStatus.OK); // HTTP 200
    }

    // Método GET (Obtener Cuenta por Owner 'getAccountByOwnerId' - SERVICIO)
    @GetMapping("/owner/{oid}")
    /*public List<Account> getAccountByOwnerId(@PathVariable("oid") Long ownerId) {
        return service.getAccountByOwnerId(ownerId); // HTTP 200 OK
        //return repo.findByOwnerId(ownerId); // HTTP 200 OK
    }*/
    public ResponseEntity<List<Account>> getAccountByOwnerId(@PathVariable("oid") Long ownerId) {
        return new ResponseEntity<>(service.getAccountByOwnerId(ownerId), HttpStatus.ACCEPTED); // HTTP 202 Accepted
        //return new ResponseEntity<>(repo.findByOwnerId(ownerId), HttpStatus.ACCEPTED); // HTTP 202 Accepted
    }

    // Método PUT (Actualizar Cuenta por ID y Account 'updateAccount' - SERVICIO)
    // Cuando queremos actualizar un recurso, hay que indicar el recurso que queremos actualizar
    @RequestMapping(value = "/{aid}", method = RequestMethod.PUT)
    public Account updateAccount(@PathVariable("aid") Long id, @RequestBody Account cuenta) {
        if (id == cuenta.getId()) return service.updateAccount(id, cuenta);
        else throw new RuntimeException();
    }

    // Método PUT (Añadir Saldo por ID + Cantidad + OwnerId Account 'addBalance' - SERVICIO)
    @PutMapping("/addBalance/{aid}/{sdo}/{own}")
    public ResponseEntity<Account> addBalance(
            @PathVariable("aid") Long id,
            @PathVariable("sdo") int saldo,
            @PathVariable("own") Long ownerId,
            @RequestBody Account cuenta) {
        if (id == cuenta.getId())
            return new ResponseEntity<>(service.addBalance(id, saldo, ownerId), HttpStatus.ACCEPTED);
        else throw new GlobalException(id);
        /*else {
            return new ResponseEntity<>(new ApiResponse("Id y product.id deben coincidir", HttpStatus.PRECONDITION_FAILED.is4xxClientError()), HttpStatus.PRECONDITION_FAILED);
        }*/
    }

    // Método PUT (Retirar Saldo por ID + Cantidad + OwnerId Account 'withdrawBalance' - SERVICIO)
    @PutMapping("/withdrawBalance/{aid}/{sdo}/{own}")
    public ResponseEntity<Account> withdrawBalance(
            @PathVariable("aid") Long id,
            @PathVariable("sdo") int saldo,
            @PathVariable("own") Long ownerId,
            @RequestBody Account cuenta) {
        if (id == cuenta.getId())
            return new ResponseEntity<>(service.withdrawBalance(id, saldo, ownerId), HttpStatus.ACCEPTED);
        else throw new GlobalException(id);
    }

    // Método DELETE (Borrar Cuenta por ID 'delete' - SERVICIO)
    /*@RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("pid") Long id) {
        service.delete(id); // HTTP 200 OK
        //repo.deleteById(id); // HTTP 200 OK
    }*/
    @DeleteMapping(value = "/{pid}")
    public ResponseEntity delete(@PathVariable("pid") Long id) {
        service.delete(id); // HTTP 204 No Content
        //repo.deleteById(id); // HTTP 204 No Content
        return ResponseEntity.noContent().build();
    }

    // Método DELETE (Borrar Cuenta por ID 'delete' - SERVICIO) HTTP 200/204 vs. 404
    @DeleteMapping(value = "/notfound/{pid}")
    public ResponseEntity<Void> deleteNotFound(@PathVariable("pid") Long id) {
        service.delete(id); // HTTP 204 No Content
        //repo.deleteById(id); // HTTP 204 No Content
        return ResponseEntity.noContent().build();
        //new ResponseEntity<>(service.delete(id), HttpStatus.NOT_FOUND); // HTTP 404 Not Found
    }

    // Método DELETE (Borrar Cuenta por Owner 'deleteAccountsUsingOwnerId' - SERVICIO)
    @DeleteMapping(value = "/owner/{pid}")
    public ResponseEntity deleteAccountsUsingOwnerId(@PathVariable("pid") Long id) {
        service.deleteAccountsUsingOwnerId(id); // HTTP 204 No Content
        //repo.deleteById(id); // HTTP 204 No Content
        return ResponseEntity.noContent().build();
    }



}
