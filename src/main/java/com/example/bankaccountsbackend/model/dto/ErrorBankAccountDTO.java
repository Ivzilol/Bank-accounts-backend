package com.example.bankaccountsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorBankAccountDTO {

    private String errorName;

    private String errorIban;
}
