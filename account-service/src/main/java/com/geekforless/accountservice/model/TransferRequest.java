package com.geekforless.accountservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Builder
public class TransferRequest {
    @NotNull
    private Long receiverAccount;

    @NotNull
    @Min(value = 1, message = "Transfer amount must be more than zero")
    private BigDecimal amount;
}
