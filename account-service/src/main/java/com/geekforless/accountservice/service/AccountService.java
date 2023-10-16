package com.geekforless.accountservice.service;

import com.geekforless.accountservice.exception.AccountNotFoundException;
import com.geekforless.accountservice.exception.AccountOverdraftException;
import com.geekforless.accountservice.model.TransferRequest;
import com.geekforless.accountservice.repository.entity.Account;

public interface AccountService {
    void handleTransfer(Long id, TransferRequest request) throws AccountNotFoundException, AccountOverdraftException;

    Account getAccountDetails(Long id) throws AccountNotFoundException;

}
