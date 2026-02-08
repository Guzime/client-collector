package ru.tolmachev.clientcollector.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Service
@RestController
public class ClientController {

    @GetMapping("/start")
    public ResponseEntity<String> startLoad() {
        return ResponseEntity.ok("success");
    }
}
