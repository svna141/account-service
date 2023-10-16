package com.geekforless.accountservice;

import com.geekforless.accountservice.exception.AccountNotFoundException;
import com.geekforless.accountservice.exception.AccountOverdraftException;
import com.geekforless.accountservice.model.TransferRequest;
import com.geekforless.accountservice.repository.AccountRepository;
import com.geekforless.accountservice.repository.entity.Account;
import com.geekforless.accountservice.service.AccountServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.geekforless.accountservice.TestHelper.getTransferRequest;
import static com.geekforless.accountservice.exception.ErrorMessage.ACCOUNT_NOT_FOUND;
import static com.geekforless.accountservice.exception.ErrorMessage.ACCOUNT_OVERDRAFT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountServiceTest {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountServiceImpl service;

    private final long id = 1;
    private final long id2 = 2L;

    @Test
    @SneakyThrows
    @DisplayName("Should transfer money")
    public void shouldTransferMoney() {
        service.handleTransfer(id, getTransferRequest());
        Account account = service.getAccountDetails(id);
        Account accountReceiver = service.getAccountDetails(id2);

        assertEquals(new BigDecimal("140.00"), account.getBalance());
        assertEquals(new BigDecimal("20.00"), accountReceiver.getBalance());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw overdraft exception")
    public void shouldThrowOverdraftException() {
        TransferRequest request = TransferRequest.builder().receiverAccount(id2).amount(new BigDecimal("200.00")).build();
        Exception exception = assertThrows(AccountOverdraftException.class, () -> service.handleTransfer(id, request));
        String expectedMessage = String.format(ACCOUNT_OVERDRAFT, id);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    @SneakyThrows
    @DisplayName("Should throw account not found exception")
    public void shouldThrowAccountNotFoundException() {
        Exception exception = assertThrows(AccountNotFoundException.class, () -> service.getAccountDetails(20L));
        String expectedMessage = String.format(ACCOUNT_NOT_FOUND, 20L);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @SneakyThrows
    @DisplayName("Should get account details")
    public void shouldGetAccountDetails() {
        Account account = service.getAccountDetails(id);

        assertEquals(id, account.getId());
    }
}
