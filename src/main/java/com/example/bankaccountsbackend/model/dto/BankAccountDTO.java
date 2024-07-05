package com.example.bankaccountsbackend.model.dto;

import com.example.bankaccountsbackend.model.enums.Status;
import com.example.bankaccountsbackend.validation.annotation.UniqueIban;
import com.example.bankaccountsbackend.validation.annotation.UniqueName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccountDTO {

    @UniqueName(message = "Bank account name already exist")
    @NotNull
    private String name;
    @NotNull
    @UniqueIban()
    private String iban;

    private Status status;


    private BigDecimal availableAmount;


    private LocalDateTime createdOn;


    private LocalDateTime ModifiedOn;
}
