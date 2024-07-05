package com.example.bankaccountsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {

    private String ibanSend;

    private String ibanReceives;

    private BigDecimal amount;
}
