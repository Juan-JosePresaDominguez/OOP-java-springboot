package com.microcompany.accountsservice.exception;


import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Maneja las excepciones globalmente
public class AccountNotFoundException extends GlobalException {
    protected static final long serialVersionUID = 2L;

    public AccountNotFoundException() {
        super("Account not found");
    }

    public AccountNotFoundException(Long accountId) {
        super("Account with id: " + accountId + " not found");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
