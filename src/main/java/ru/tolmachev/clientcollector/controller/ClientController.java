package ru.tolmachev.clientcollector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
        FinancialClientDto dto = new FinancialClientDto(
                "cl-100045",
                "ext-778899",
                "INDIVIDUAL",
                "Ivan",
                "Petrov",
                "Sergeevich",
                LocalDate.of(1988, 3, 21),
                "770123456789",
                "4510123456",
                "ivan.petrov@bank.example",
                "+79991234567",
                "RU",
                "40817810099910004321",
                "RUB",
                new BigDecimal("152340.75"),
                new BigDecimal("132340.75"),
                new BigDecimal("50000.00"),
                new BigDecimal("18340.25"),
                37,
                KycStatus.PASSED,
                false,
                true,
                false,
                LocalDate.of(2022, 11, 15),
                LocalDate.of(2024, 12, 5)
        );

        return ResponseEntity.ok(dto);
    }
}
