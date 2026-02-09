package ru.tolmachev.clientcollector.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialClientDto {

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("clientType")
    private String clientType;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("birthDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonProperty("taxId")
    private String taxId;

    @JsonProperty("passportNumber")
    private String passportNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("residencyCountry")
    private String residencyCountry;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountCurrency")
    private String accountCurrency;

    @JsonProperty("accountBalance")
    private BigDecimal accountBalance;

    @JsonProperty("availableBalance")
    private BigDecimal availableBalance;

    @JsonProperty("creditLimit")
    private BigDecimal creditLimit;

    @JsonProperty("debtAmount")
    private BigDecimal debtAmount;

    @JsonProperty("riskScore")
    private Integer riskScore;

    @JsonProperty("kycStatus")
    private KycStatus kycStatus;

    @JsonProperty("amlFlag")
    private Boolean amlFlag;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("blocked")
    private Boolean blocked;

    @JsonProperty("createdAt")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonProperty("updatedAt")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;
}
