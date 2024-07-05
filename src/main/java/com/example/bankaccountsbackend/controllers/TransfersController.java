package com.example.bankaccountsbackend.controllers;

import com.example.bankaccountsbackend.custome.CustomResponse;
import com.example.bankaccountsbackend.model.dto.TransferDTO;
import com.example.bankaccountsbackend.model.dto.TransferDetailsDTO;
import com.example.bankaccountsbackend.model.dto.TransfersDTO;
import com.example.bankaccountsbackend.model.entity.UserEntity;
import com.example.bankaccountsbackend.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer")
public class TransfersController {

    private final TransferService transferService;

    public TransfersController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransfer(@RequestBody TransferDTO transferDTO,
                                            @AuthenticationPrincipal UserEntity user) {
        boolean isTransfer = this.transferService.createTransfer(transferDTO, user);
        CustomResponse customResponse = new CustomResponse();
        if (isTransfer) {
            customResponse.setCustom("Valid transfer");
        } else {
            customResponse.setCustom("Invalid transfer");
        }
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getTransferById(@PathVariable Long accountId) {
        List<TransfersDTO> transfersDTO = this.transferService.getTransfersById(accountId);
        return ResponseEntity.ok(transfersDTO);
    }

    @GetMapping("/details/{transferId}")
    public ResponseEntity<?> getTransferDetails(@PathVariable Long transferId,
                                                @AuthenticationPrincipal UserEntity user) {
        TransferDetailsDTO transferDetailsDTO = this.transferService.getDetails(transferId, user);
        return ResponseEntity.ok(transferDetailsDTO);
    }
}
