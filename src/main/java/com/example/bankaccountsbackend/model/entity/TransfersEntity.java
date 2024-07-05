package com.example.bankaccountsbackend.model.entity;

import com.example.bankaccountsbackend.model.enums.TransferType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransfersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ownerAccountId;

    @Column(nullable = false)
    private Long BeneficiaryAccountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferType transferType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column
    private LocalDateTime modifiedOn;
}
