package ru.tolmachev.clientcollector.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountCurrency")
    private String accountCurrency;

    @JsonProperty("accountBalance")
    private BigDecimal accountBalance;

    @JsonProperty("availableBalance")
    private BigDecimal availableBalance;

    @JsonProperty("creditLimit")
    private BigDecimal creditLimit;

    @JsonProperty("debtAmount")
    private BigDecimal debtAmount;
}
