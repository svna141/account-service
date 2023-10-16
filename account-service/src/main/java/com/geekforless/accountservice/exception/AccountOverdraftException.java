package com.geekforless.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AccountOverdraftException extends Exception {
    public AccountOverdraftException(String message) {
            super(message);
        }
}
