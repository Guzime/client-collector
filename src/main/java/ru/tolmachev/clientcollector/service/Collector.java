package ru.tolmachev.clientcollector.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tolmachev.clientcollector.domain.FinancialClientDto;

@Slf4j
@Service
public class Collector {

    public FinancialClientDto load() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FinancialClientDto> generateClient = restTemplate
                .getForEntity("http://localhost:8089/client", FinancialClientDto.class);
        return generateClient.getBody();
    }
}
