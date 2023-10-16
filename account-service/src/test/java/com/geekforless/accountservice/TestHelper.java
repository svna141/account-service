package com.geekforless.accountservice;

import com.geekforless.accountservice.model.TransferRequest;
import com.geekforless.accountservice.repository.entity.Account;

import java.math.BigDecimal;

public class TestHelper {

    public static Account getAccount() {
        return Account.builder().id(1L).balance(new BigDecimal(100)).build();
    }

    public static TransferRequest getTransferRequest() {
        return TransferRequest.builder().receiverAccount(2L).amount(new BigDecimal(10)).build();
    }
}
