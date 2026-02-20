package ru.tolmachev.clientcollector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tolmachev.clientcollector.domain.ClientDto;
import ru.tolmachev.clientcollector.domain.FinancialClientDto;
import ru.tolmachev.clientcollector.service.Collector;

@Slf4j
@RestController
public class ClientController {

    private final Collector collector;

    public ClientController(@Autowired Collector collector) {
        this.collector = collector;
    }

    @GetMapping("/start")
    public ResponseEntity<ClientDto> startLoad() {
        return ResponseEntity.ok(collector.getClient());
    }

    @GetMapping("/startComletableFuture")
    public ResponseEntity<FinancialClientDto> startLoadCompletableFuture() {
        return ResponseEntity.ok(collector.async());
    }
}
