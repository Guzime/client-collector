package ru.tolmachev.clientcollector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.tolmachev.clientcollector.domain.FinancialClientDto;
import ru.tolmachev.clientcollector.domain.KycStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
@RestController
public class ClientController {

    @GetMapping("/start")
    public ResponseEntity<FinancialClientDto> startLoad() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FinancialClientDto> generateClient = restTemplate.getForEntity("http://localhost:8089/client", FinancialClientDto.class);
        return ResponseEntity.ok(generateClient.getBody());
    }
}
