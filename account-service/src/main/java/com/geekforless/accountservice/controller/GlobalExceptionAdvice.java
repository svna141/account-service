package com.geekforless.accountservice.controller;

import com.geekforless.accountservice.exception.AccountNotFoundException;
import com.geekforless.accountservice.exception.AccountOverdraftException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.geekforless.accountservice.exception.ErrorMessage.ACCOUNT_NOT_FOUND;
import static com.geekforless.accountservice.exception.ErrorMessage.ACCOUNT_OVERDRAFT;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ACCOUNT_NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(AccountOverdraftException.class)
    public ResponseEntity<String> handleAccountOverdraft(AccountOverdraftException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ACCOUNT_OVERDRAFT);
    }
}
