package com.example.bankaccountsbackend.model.dto;

import com.example.bankaccountsbackend.model.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransfersDTO {

    private Long id;

    private TransferType transferType;

    private BigDecimal amount;

    private LocalDateTime createdOn;
}
