package com.example.bankaccountsbackend.controllers;

import com.example.bankaccountsbackend.custome.CustomResponse;
import com.example.bankaccountsbackend.model.dto.*;
import com.example.bankaccountsbackend.model.entity.BankAccountsEntity;
import com.example.bankaccountsbackend.model.entity.UserEntity;
import com.example.bankaccountsbackend.services.BankAccountsService;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final BankAccountsService bankAccountsService;

    public BankAccountController(BankAccountsService bankAccountsService) {
        this.bankAccountsService = bankAccountsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBankAccount(@AuthenticationPrincipal UserEntity user,
                                               @RequestBody @Valid BankAccountDTO bankAccountDTO,
                                               BindingResult result) {
        ResponseEntity<ErrorBankAccountDTO> errorBankAccountDTO = errorCreateAccount(result, bankAccountDTO);
        if (errorBankAccountDTO != null) return errorBankAccountDTO;
        BankAccountsEntity bankAccounts = this.bankAccountsService.createBankAccount(user, bankAccountDTO);
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCustom("Successful create account");
        return ResponseEntity.ok(customResponse);
    }

    private ResponseEntity<ErrorBankAccountDTO> errorCreateAccount(BindingResult result, BankAccountDTO bankAccountDTO) {
        ErrorBankAccountDTO errorBankAccountDTO = new ErrorBankAccountDTO();
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            this.bankAccountsService.setErrors(errors, errorBankAccountDTO);
            return ResponseEntity.ok(errorBankAccountDTO);
        }
        return null;
    }


    @GetMapping("")
    public ResponseEntity<?> getUserAccounts(@AuthenticationPrincipal UserEntity user) {
        List<UserBankAccountsDTO> userBankAccountsDTO = this.bankAccountsService.getUserAccounts(user);
        return ResponseEntity.ok(userBankAccountsDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getOtherUsersAccounts(@AuthenticationPrincipal UserEntity user) {
        List<OtherUsersBankAccountsDTO> otherUsersBankAccountsDTOS = this.bankAccountsService.
                getOtherUsersAccounts(user);
        return ResponseEntity.ok(otherUsersBankAccountsDTOS);
    }

    @PostMapping("/status")
    public ResponseEntity<?> changeStatus(@AuthenticationPrincipal UserEntity user,
                                          @RequestBody UserBankAccountsDTO userBankAccountsDTO) {
        this.bankAccountsService.chaneStatus(user, userBankAccountsDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/edit/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId,
                                            @AuthenticationPrincipal UserEntity user) {
        UserAccountByIdDTO userAccountByIdDTO = this.bankAccountsService
                .getAccountById(accountId, user);
        return ResponseEntity.ok(userAccountByIdDTO);
    }

    @PatchMapping("/edit/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId,
                                           @RequestPart(value = "dto") UserAccountByIdDTO userAccountByIdDTO){
        boolean isUpdate = this.bankAccountsService.updateAccount(accountId, userAccountByIdDTO);
        CustomResponse customResponse = new CustomResponse();
        if (isUpdate) {
            customResponse.setCustom("Successful update account");
            return ResponseEntity.ok().body(customResponse);
        } else {
            customResponse.setCustom("Unsuccessful update account");
            return ResponseEntity.badRequest().body(customResponse);
        }
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId,
                                           @AuthenticationPrincipal UserEntity user) {
        boolean isDelete = this.bankAccountsService.deleteAccount(accountId, user);
        CustomResponse customResponse = new CustomResponse();
        if (isDelete) {
            customResponse.setCustom("Successful update account");
            return ResponseEntity.ok().body(customResponse);
        } else {
            customResponse.setCustom("Unsuccessful delete account");
            return ResponseEntity.badRequest().body(customResponse);
        }
    }
}
