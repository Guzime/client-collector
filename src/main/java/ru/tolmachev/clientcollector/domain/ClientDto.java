package ru.tolmachev.clientcollector.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ClientDto {

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("externalId")
    private String externalId;

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
