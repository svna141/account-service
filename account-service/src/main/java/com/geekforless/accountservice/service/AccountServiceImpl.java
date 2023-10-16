package com.geekforless.accountservice.service;

import com.geekforless.accountservice.exception.AccountNotFoundException;
import com.geekforless.accountservice.exception.AccountOverdraftException;
import com.geekforless.accountservice.model.TransferRequest;
import com.geekforless.accountservice.repository.AccountRepository;
import com.geekforless.accountservice.repository.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static com.geekforless.accountservice.exception.ErrorMessage.ACCOUNT_NOT_FOUND;
import static com.geekforless.accountservice.exception.ErrorMessage.ACCOUNT_OVERDRAFT;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void handleTransfer(Long id, TransferRequest request) throws AccountNotFoundException, AccountOverdraftException {
        Account sender = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(String.format(ACCOUNT_NOT_FOUND, id)));
        checkIfNoOverdraft(sender, request.getAmount());

        accountRepository.withdraw(id, request.getAmount());
        accountRepository.popup(request.getReceiverAccount(), request.getAmount());
    }

    @Override
    public Account getAccountDetails(Long id) throws AccountNotFoundException {
        return accountRepository.findById(id).orElseThrow(() -> {
                    log.error(String.format(ACCOUNT_NOT_FOUND, id));
                    return new AccountNotFoundException(String.format(ACCOUNT_NOT_FOUND, id));
                }
        );
    }

    private void checkIfNoOverdraft(Account sender, BigDecimal amount) throws AccountOverdraftException {
        if (sender.getBalance().compareTo(amount) < 0) {
            log.error(String.format(ACCOUNT_OVERDRAFT, sender.getId()));
            throw new AccountOverdraftException(String.format(ACCOUNT_OVERDRAFT, sender.getId()));
        }
    }

}
