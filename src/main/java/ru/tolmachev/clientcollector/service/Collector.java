package ru.tolmachev.clientcollector.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tolmachev.clientcollector.domain.AccountDto;
import ru.tolmachev.clientcollector.domain.ClientDto;
import ru.tolmachev.clientcollector.domain.FinancialClientDto;

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
    public ClientDto getClient() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientDto> client = restTemplate
                .getForEntity("http://localhost:8089/client", ClientDto.class);
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

    /**
     * Chain two independent http call with using CompleatbleFuture
     * @return Full client info
     */
    public FinancialClientDto async() {
        //todo example error + timeout
        CompletableFuture<ClientDto> futureClient = CompletableFuture
                .supplyAsync(this::getClient);
        CompletableFuture<List<AccountDto>> futureAccount = CompletableFuture
                .supplyAsync(this::getAccount);

        CompletableFuture<FinancialClientDto> futureEnrichClient = CompletableFuture.allOf(futureAccount, futureClient)
                .thenApply(x -> {
                    ClientDto client = futureClient.join();
                    List<AccountDto> accounts = futureAccount.join();
                    return new FinancialClientDto(client, accounts);
                });

        return futureEnrichClient.join();
    }
}
