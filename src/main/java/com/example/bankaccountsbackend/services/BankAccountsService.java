package com.example.bankaccountsbackend.services;

import com.example.bankaccountsbackend.model.dto.*;
import com.example.bankaccountsbackend.model.entity.BankAccountsEntity;
import com.example.bankaccountsbackend.model.entity.UserEntity;
import com.example.bankaccountsbackend.model.enums.Status;
import com.example.bankaccountsbackend.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountsService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountsService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccountsEntity createBankAccount(UserEntity user, BankAccountDTO bankAccountDTO) {
        BankAccountsEntity bankAccounts = new BankAccountsEntity();
        bankAccounts.setName(bankAccountDTO.getName());
        bankAccounts.setIban(bankAccountDTO.getIban());
        bankAccounts.setStatus(Status.Active);
        bankAccounts.setAvailableAmount(bankAccountDTO.getAvailableAmount());
        bankAccounts.setCreatedOn(LocalDateTime.now());
        bankAccounts.setModifiedOn(LocalDateTime.now());
        bankAccounts.setUser(user);
        this.bankAccountRepository.save(bankAccounts);
        return  bankAccounts;
    }


    public Optional<BankAccountsEntity> findAccountByName(String name) {
       return this.bankAccountRepository.findByName(name);
    }

    public Optional<BankAccountsEntity> findByIban(String iban) {
        return this.bankAccountRepository.findByIban(iban);
    }

    public void setErrors(List<String> errors, ErrorBankAccountDTO errorBankAccountDTO) {
        for (String error : errors) {
            switch (error){
                case "Bank account name already exist" -> errorBankAccountDTO.setErrorName("Bank account name already exist");
                case "Iban already exist" -> errorBankAccountDTO.setErrorIban("Iban already exist");
            }
        }
    }

    public List<UserBankAccountsDTO> getUserAccounts(UserEntity user) {
        return this.bankAccountRepository.findAllAccountById(user.getId());
    }

    public List<OtherUsersBankAccountsDTO> getOtherUsersAccounts(UserEntity user) {
        return this.bankAccountRepository.findOtherUsersBankAccounts(user.getId());
    }

    public void chaneStatus(UserEntity user, UserBankAccountsDTO userBankAccountsDTO) {
        Optional<BankAccountsEntity> bankAccounts = this.bankAccountRepository.
                findById(userBankAccountsDTO.getId());
        if (bankAccounts.get().getStatus().equals(Status.Active)) {
            bankAccounts.get().setStatus(Status.Frozen);
        } else {
            bankAccounts.get().setStatus(Status.Active);
        }
        this.bankAccountRepository.save(bankAccounts.get());
    }

    public UserAccountByIdDTO getAccountById(Long accountId, UserEntity user) {
        return this.bankAccountRepository.findAccountById(accountId);
    }

    public boolean updateAccount(Long accountId, UserAccountByIdDTO userAccountByIdDTO) {
        Optional<BankAccountsEntity> bankAccounts = this.bankAccountRepository.
                findById(accountId);
        if (bankAccounts.isPresent()) {
            bankAccounts.get().setName(userAccountByIdDTO.getName());
            bankAccounts.get().setIban(userAccountByIdDTO.getIban());
            bankAccounts.get().setModifiedOn(LocalDateTime.now());
            this.bankAccountRepository.save(bankAccounts.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAccount(Long accountId, UserEntity user) {
        Optional<BankAccountsEntity> bankAccounts = this.bankAccountRepository.
                findById(accountId);
        if (bankAccounts.isPresent()) {
            this.bankAccountRepository.delete(bankAccounts.get());
            return true;
        } else {
            return false;
        }
    }
}
