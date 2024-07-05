package com.example.bankaccountsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDetailsDTO {

    private String createdOn;

    private String time;

    private BigDecimal amount;

    private String transferType;

}
