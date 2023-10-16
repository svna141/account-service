package com.geekforless.accountservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekforless.accountservice.controller.AccountController;
import com.geekforless.accountservice.exception.AccountNotFoundException;
import com.geekforless.accountservice.model.TransferRequest;
import com.geekforless.accountservice.service.AccountService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.geekforless.accountservice.TestHelper.getAccount;
import static com.geekforless.accountservice.TestHelper.getTransferRequest;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@ExtendWith(SpringExtension.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService service;

    @Test
    @SneakyThrows
    @DisplayName("Should return account details with status 200")
    public void shouldGetAccountDetails() {
        when(service.getAccountDetails(anyLong())).thenReturn(getAccount());

        this.mockMvc.perform(get("/api/accounts/1")).andDo(print()).andExpect(status().isOk());

        verify(service, times(1)).getAccountDetails(anyLong());
        verifyNoMoreInteractions(service);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw account not found exception with status code 400")
    public void shouldThrowAccountNotFoundException() {
        when(service.getAccountDetails(anyLong())).thenThrow(new AccountNotFoundException("not found"));

        this.mockMvc.perform(get("/api/accounts/1")).andDo(print()).andExpect(status().isBadRequest());

        verify(service, times(1)).getAccountDetails(anyLong());
        verifyNoMoreInteractions(service);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return account details with status 200")
    public void shouldDoTransfer() {
        doNothing().when(service).handleTransfer(anyLong(), any(TransferRequest.class));

        this.mockMvc.perform(post("/api/accounts/1/transfer").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(getTransferRequest()))).andExpect(status().isNoContent());

        verify(service, times(1)).handleTransfer(anyLong(), any(TransferRequest.class));
        verifyNoMoreInteractions(service);
    }


}
