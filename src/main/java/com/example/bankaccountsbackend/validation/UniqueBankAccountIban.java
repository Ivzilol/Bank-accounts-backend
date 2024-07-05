package com.example.bankaccountsbackend.validation;

import com.example.bankaccountsbackend.services.BankAccountsService;
import com.example.bankaccountsbackend.validation.annotation.UniqueIban;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBankAccountIban implements ConstraintValidator<UniqueIban, String> {

    private final BankAccountsService bankAccountsService;

    public UniqueBankAccountIban(BankAccountsService bankAccountsService) {
        this.bankAccountsService = bankAccountsService;
    }

    @Override
    public boolean isValid(String iban, ConstraintValidatorContext constraintValidatorContext) {
        return this.bankAccountsService.findByIban(iban).isEmpty();
    }
}
