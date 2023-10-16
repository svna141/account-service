package com.geekforless.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.geekforless.accountservice.exception.ErrorMessage.ACCOUNT_NOT_FOUND;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ACCOUNT_NOT_FOUND)
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
