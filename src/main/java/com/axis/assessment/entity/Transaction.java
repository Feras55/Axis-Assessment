package com.axis.assessment.entity;

import com.axis.assessment.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "transaction_type")
    private TransactionType transactionType;


}