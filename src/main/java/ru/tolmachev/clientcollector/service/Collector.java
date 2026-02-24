package ru.tolmachev.clientcollector.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tolmachev.clientcollector.domain.AccountDto;
import ru.tolmachev.clientcollector.domain.ClientDto;
import ru.tolmachev.clientcollector.domain.FinancialClientDto;
import ru.tolmachev.clientcollector.domain.db.ClientEntity;
import ru.tolmachev.clientcollector.domain.db.ClientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class Collector {

    private final ClientRepository clientRepository;

    public Collector(@Autowired ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

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
                    saveClient(client);
                    List<AccountDto> accounts = futureAccount.join();
                    return new FinancialClientDto(client, accounts);
                });

        return futureEnrichClient.join();
    }

    public void saveClient(ClientDto client) {
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setClientId(client.getClientId());
        clientEntity.setExternalId(client.getExternalId());
        clientEntity.setFirstName(client.getFirstName());
        clientEntity.setLastName(client.getLastName());
        clientEntity.setMiddleName(client.getMiddleName());
        clientEntity.setBirthDate(client.getBirthDate());
        clientEntity.setTaxId(client.getTaxId());
        clientEntity.setPassportNumber(client.getPassportNumber());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setResidencyCountry(client.getResidencyCountry());
        clientEntity.setRiskScore(client.getRiskScore());
        clientEntity.setKycStatus(client.getKycStatus());
        clientEntity.setAmlFlag(client.getAmlFlag());
        clientEntity.setActive(client.getActive());
        clientEntity.setBlocked(client.getBlocked());
        clientEntity.setCreatedAt(client.getCreatedAt());
        clientEntity.setUpdatedAt(client.getUpdatedAt());

        clientRepository.save(clientEntity);

    }
}
