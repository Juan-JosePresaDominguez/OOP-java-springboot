package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.exception.AccountNotfoundException;
import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.payload.ApiResponse;
import com.microcompany.accountsservice.payload.MoneyForOwner;
import com.microcompany.accountsservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountServiceController {

    @Autowired
    private AccountService accountService;


    @GetMapping("")
    public ResponseEntity getAccount() {
        List<Account> accs = accountService.getAccounts();
        if (accs != null && accs.size() > 0) return ResponseEntity.status(HttpStatus.OK).body(accs);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Vacio", false));
    }

    @PostMapping("")
    public ResponseEntity<Account> createAccount(
            @RequestBody @Valid Account account
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(
            @PathVariable @Min(1) Long id
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(id));
        } catch (AccountNotfoundException e) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            throw new AccountNotfoundException("La lista de productos está vacía");
        }
    }

    // update account

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(
            @RequestBody Account account,
            @PathVariable @Min(1) Long id
    ) {
        account.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.updateAccount(id, account));
    }

    // Add Money
   /* @PutMapping("/addmoney/{id}")
    public ResponseEntity<Account> addMoney(
            @PathVariable Long id,
            @RequestParam int amount,
            @RequestParam Long ownerId
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.addBalance(id, amount, ownerId));
    }*/

    @PutMapping("/addmoney/{id}")
    public ResponseEntity<Account> addMoney(
            @PathVariable @Min(1) Long id,
            @RequestBody MoneyForOwner moneyForOwner
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.addBalance(id, moneyForOwner.getAmount(), moneyForOwner.getOwnerId()));
    }

    // withdraw Money
   /* @PutMapping("/withdraw/{id}")
    public ResponseEntity<Account> withdraw(
            @PathVariable Long id,
            @RequestParam int amount,
            @RequestParam Long ownerId
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.withdrawBalance(id, amount, ownerId));
    }*/
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<Account> withdraw(
            @PathVariable @Min(1) Long id,
            @RequestBody MoneyForOwner moneyForOwner
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.withdrawBalance(id, moneyForOwner.getAmount(), moneyForOwner.getOwnerId()));
    }

    // Delete Account
    /*@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiResponse deleteAccount(
            @PathVariable Long id
    ) {
        this.accountService.delete(id);
        return new ApiResponse("Account is Successfully Deleted", true);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(
            @PathVariable @Min(1) Long id
    ) {
        this.accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Delete Account using ownerId

    /*@DeleteMapping("user/{ownerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiResponse deleteAccountByUserId(
            @PathVariable Long ownerId
    ) {
        this.accountService.deleteAccountsUsingOwnerId(ownerId);
        return new ApiResponse(" Accounts with given userId is deleted Successfully", true);

    }*/

    @DeleteMapping("user/{ownerId}")
    public ResponseEntity deleteAccountByUserId(
            @PathVariable @Min(1) Long ownerId
    ) {
        this.accountService.deleteAccountsUsingOwnerId(ownerId);
        return ResponseEntity.noContent().build();

    }
}
