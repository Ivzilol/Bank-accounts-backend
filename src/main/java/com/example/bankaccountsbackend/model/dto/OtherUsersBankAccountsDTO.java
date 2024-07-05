package com.example.bankaccountsbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtherUsersBankAccountsDTO {

    private Long id;

    private String name;

    private String iban;

}
