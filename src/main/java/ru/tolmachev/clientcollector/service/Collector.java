package ru.tolmachev.clientcollector.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tolmachev.clientcollector.domain.FinancialClientDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class Collector {

    //todo нужно реализовать 3 примера :
    // 1. CompletableFuture + threadPool
    // 2. Virtual Threads
    // 3. Web Client
    public FinancialClientDto load() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FinancialClientDto> generateClient = restTemplate
                .getForEntity("http://localhost:8089/client", FinancialClientDto.class);
        log.info("Accept response {}", generateClient);
        return generateClient.getBody();
    }

    //todo ненадо цепочку вызово делать
    @SneakyThrows
    public List<FinancialClientDto> async() {
        List<FinancialClientDto> clients = new ArrayList<>();
        CompletableFuture<Boolean> future = CompletableFuture
                .supplyAsync(this::load)
                .thenApply(clients::add)
                .supplyAsync(this::load)
                .thenApply(clients::add)
                .supplyAsync(this::load)
                .thenApply(clients::add);
        future.get();
        return clients;
    }
}
