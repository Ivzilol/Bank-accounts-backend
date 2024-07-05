package com.example.bankaccountsbackend.model.dto;

import com.example.bankaccountsbackend.validation.annotation.UniqueIban;
import com.example.bankaccountsbackend.validation.annotation.UniqueName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccountByIdDTO {


    private String name;


    private String iban;
}
