package ru.tolmachev.clientcollector.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tolmachev.clientcollector.domain.AccountDto;
import ru.tolmachev.clientcollector.domain.FinancialClientDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class Collector {

    //todo нужно реализовать 3 примера :
    // 1. CompletableFuture ✅ + threadPool
    // 2. Virtual Threads
    // 3. Web Client
    public FinancialClientDto getClient() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FinancialClientDto> client = restTemplate
                .getForEntity("http://localhost:8089/client", FinancialClientDto.class);
        log.info("Accept client {}", client);
        return client.getBody();
    }

    public List<AccountDto> getAccount() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AccountDto[]> accounts = restTemplate
                .getForEntity("http://localhost:8089/account", AccountDto[].class);
        log.info("Accept account {}", accounts);
        return Arrays.asList(accounts.getBody());
    }

    public FinancialClientDto async() {
        CompletableFuture<FinancialClientDto> futureClient = CompletableFuture
                .supplyAsync(this::getClient);
        CompletableFuture<List<AccountDto>> futureAccount = CompletableFuture
                .supplyAsync(this::getAccount);

        CompletableFuture<FinancialClientDto> futureEnrichClient = CompletableFuture.allOf(futureAccount, futureClient)
                .thenApply(x -> {
                    FinancialClientDto financialClientDto = futureClient.join();
                    List<AccountDto> accounts = futureAccount.join();
                    financialClientDto.setAccounts(accounts);
                    return financialClientDto;
                });

        return futureEnrichClient.join();
    }
}
