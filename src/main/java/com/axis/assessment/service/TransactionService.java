package com.axis.assessment.service;


import com.axis.assessment.entity.Transaction;
import com.axis.assessment.payload.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO deposit(TransactionDTO transactionDTO);

    TransactionDTO withdraw(TransactionDTO transactionDTO);

    List<TransactionDTO> getTransactions(Long accountId);

}
