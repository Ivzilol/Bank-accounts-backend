package com.example.bankaccountsbackend.repository;

import com.example.bankaccountsbackend.model.dto.OtherUsersBankAccountsDTO;
import com.example.bankaccountsbackend.model.dto.UserAccountByIdDTO;
import com.example.bankaccountsbackend.model.dto.UserBankAccountsDTO;
import com.example.bankaccountsbackend.model.entity.BankAccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountsEntity, Long> {
    Optional<BankAccountsEntity> findByName(String name);

    Optional<BankAccountsEntity> findByIban(String iban);

    @Query("select new com.example.bankaccountsbackend.model.dto.UserBankAccountsDTO(" +
            " ba.id, ba.name, ba.iban, ba.status, ba.availableAmount)" +
            " from BankAccountsEntity as ba" +
            " where ba.user.id = :id")
    List<UserBankAccountsDTO> findAllAccountById(Long id);

    @Query("select new com.example.bankaccountsbackend.model.dto.OtherUsersBankAccountsDTO(" +
            " ba.id, ba.name, ba.iban)" +
            " from BankAccountsEntity as ba" +
            " where ba.user.id <> :id")
    List<OtherUsersBankAccountsDTO> findOtherUsersBankAccounts(Long id);

    @Query("select new com.example.bankaccountsbackend.model.dto.UserAccountByIdDTO(" +
            "ba.name, ba.iban)" +
            " from BankAccountsEntity as ba" +
            " where ba.id = :accountId")
    UserAccountByIdDTO findAccountById(Long accountId);
}
