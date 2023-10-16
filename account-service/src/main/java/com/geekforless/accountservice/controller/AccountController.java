package com.geekforless.accountservice.controller;

import com.geekforless.accountservice.exception.AccountNotFoundException;
import com.geekforless.accountservice.exception.AccountOverdraftException;
import com.geekforless.accountservice.model.TransferRequest;
import com.geekforless.accountservice.repository.entity.Account;
import com.geekforless.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts/")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("{id}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleTransfer(@PathVariable("id") Long id,
                               @RequestBody @Valid TransferRequest request) throws AccountNotFoundException, AccountOverdraftException {
       accountService.handleTransfer(id,request);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountDetails(@PathVariable("id") Long id) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.getAccountDetails(id));
    }

}
