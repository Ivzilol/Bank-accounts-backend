package com.example.bankaccountsbackend.model.dto;

import com.example.bankaccountsbackend.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBankAccountsDTO {

    private Long id;

    private String name;

    private String iban;

    private Status status;

    private BigDecimal availableAmount;

}
