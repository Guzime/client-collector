package ru.tolmachev.clientcollector.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FinancialClientDto {
    @JsonProperty("client")
    ClientDto client;
    @JsonProperty("accounts")
    List<AccountDto> accounts;
}
