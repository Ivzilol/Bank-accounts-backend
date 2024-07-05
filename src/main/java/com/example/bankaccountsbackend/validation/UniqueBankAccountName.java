package com.example.bankaccountsbackend.validation;

import com.example.bankaccountsbackend.services.BankAccountsService;
import com.example.bankaccountsbackend.validation.annotation.UniqueName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBankAccountName implements ConstraintValidator<UniqueName, String > {

    private final BankAccountsService bankAccountsService;

    public UniqueBankAccountName(BankAccountsService bankAccountsService) {
        this.bankAccountsService = bankAccountsService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return this.bankAccountsService.findAccountByName(name).isEmpty();
    }
}
