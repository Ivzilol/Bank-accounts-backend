package com.example.bankaccountsbackend.services;

import com.example.bankaccountsbackend.model.dto.TransferDTO;
import com.example.bankaccountsbackend.model.dto.TransferDetailsDTO;
import com.example.bankaccountsbackend.model.dto.TransfersDTO;
import com.example.bankaccountsbackend.model.entity.BankAccountsEntity;
import com.example.bankaccountsbackend.model.entity.TransfersEntity;
import com.example.bankaccountsbackend.model.entity.UserEntity;
import com.example.bankaccountsbackend.model.enums.Status;
import com.example.bankaccountsbackend.model.enums.TransferType;
import com.example.bankaccountsbackend.repository.BankAccountRepository;
import com.example.bankaccountsbackend.repository.TransfersRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    private final TransfersRepository transfersRepository;

    private final BankAccountRepository bankAccountRepository;

    public TransferService(TransfersRepository transfersRepository, BankAccountRepository bankAccountRepository) {
        this.transfersRepository = transfersRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public boolean createTransfer(TransferDTO transferDTO, UserEntity user) {
        Optional<BankAccountsEntity> senderAccount = this.bankAccountRepository
                .findByIban(transferDTO.getIbanSend());
        Optional<BankAccountsEntity> receiverAccount = this.bankAccountRepository
                .findByIban(transferDTO.getIbanReceives());
        if (senderAccount.get().getAvailableAmount().compareTo(transferDTO.getAmount()) >= 0
                && receiverAccount.get().getStatus() == Status.Active) {
            processingSenderTransfer(transferDTO, senderAccount, receiverAccount);
            processingReceiverTransfer(transferDTO, senderAccount, receiverAccount);
            return true;
        } else {
            return false;
        }
    }

    private void processingReceiverTransfer(TransferDTO transferDTO,
                                            Optional<BankAccountsEntity> senderAccount,
                                            Optional<BankAccountsEntity> receiverAccount) {
        TransfersEntity receiverTransfer = new TransfersEntity();
        receiverTransfer.setAmount(transferDTO.getAmount());
        receiverTransfer.setOwnerAccountId(senderAccount.get().getId());
        receiverTransfer.setBeneficiaryAccountId(receiverAccount.get().getId());
        receiverTransfer.setTransferType(TransferType.Credit);
        receiverTransfer.setAmount(transferDTO.getAmount());
        receiverTransfer.setCreatedOn(LocalDateTime.now());
        receiverTransfer.setModifiedOn(LocalDateTime.now());
        this.transfersRepository.save(receiverTransfer);
        receiverAccount.get().setAvailableAmount(receiverAccount.get().getAvailableAmount()
                .add(transferDTO.getAmount()));
        this.bankAccountRepository.save(receiverAccount.get());
    }

    private void processingSenderTransfer(TransferDTO transferDTO, Optional<BankAccountsEntity> senderAccount, Optional<BankAccountsEntity> receiverAccount) {
        TransfersEntity senderTransfer = new TransfersEntity();
        senderTransfer.setAmount(transferDTO.getAmount());
        senderTransfer.setOwnerAccountId(senderAccount.get().getId());
        senderTransfer.setBeneficiaryAccountId(receiverAccount.get().getId());
        senderTransfer.setTransferType(TransferType.Debit);
        senderTransfer.setAmount(transferDTO.getAmount());
        senderTransfer.setCreatedOn(LocalDateTime.now());
        senderTransfer.setModifiedOn(LocalDateTime.now());
        this.transfersRepository.save(senderTransfer);
        senderAccount.get().setAvailableAmount(senderAccount.get().getAvailableAmount()
                .subtract(transferDTO.getAmount()));
        this.bankAccountRepository.save(senderAccount.get());
    }

    public List<TransfersDTO> getTransfersById(Long accountId) {
        return this.transfersRepository.getTransfersByOwnerAccountId(accountId);
    }

    public TransferDetailsDTO getDetails(Long transferId, UserEntity user) {
        Optional<TransfersEntity> transfersEntity = this.transfersRepository.findById(transferId);
        TransferDetailsDTO transferDetailsDTO = new TransferDetailsDTO();
        LocalDateTime now = transfersEntity.get().getCreatedOn();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(formatterDate);
        transferDetailsDTO.setCreatedOn(date);
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = now.format(formatterTime);
        transferDetailsDTO.setTime(time);
        transferDetailsDTO.setAmount(transfersEntity.get().getAmount());
        transferDetailsDTO.setTransferType(String.valueOf(transfersEntity.get().getTransferType()));
        return transferDetailsDTO;
    }
}
