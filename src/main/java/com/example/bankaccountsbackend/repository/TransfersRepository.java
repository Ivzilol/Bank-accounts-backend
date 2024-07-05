package com.example.bankaccountsbackend.repository;

import com.example.bankaccountsbackend.model.dto.TransfersDTO;
import com.example.bankaccountsbackend.model.entity.TransfersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfersRepository extends JpaRepository<TransfersEntity, Long> {

    @Query("select new com.example.bankaccountsbackend.model.dto.TransfersDTO(" +
            " t.id, t.transferType, t.amount, t.createdOn)" +
            " from TransfersEntity as t" +
            " where t.ownerAccountId = :accountId")
    List<TransfersDTO> getTransfersByOwnerAccountId(Long accountId);
}
