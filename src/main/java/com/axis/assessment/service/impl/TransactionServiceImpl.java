package com.axis.assessment.service.impl;


import com.axis.assessment.entity.Account;
import com.axis.assessment.entity.Transaction;
import com.axis.assessment.enums.TransactionType;
import com.axis.assessment.exception.RequestValidationException;
import com.axis.assessment.exception.ResourceNotFoundException;
import com.axis.assessment.payload.TransactionDTO;
import com.axis.assessment.repository.AccountRepository;
import com.axis.assessment.repository.TransactionRepository;
import com.axis.assessment.service.AccountService;
import com.axis.assessment.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.axis.assessment.utils.ApplicationConstants.*;


@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;
    private ModelMapper modelMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, AccountService accountService, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TransactionDTO deposit(TransactionDTO transactionDTO) {

        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT, ID, transactionDTO.getAccountId()));

        account.setBalance(account.getBalance() + transactionDTO.getAmount());

        Transaction transaction = mapToEntity(transactionDTO);

        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        Transaction newTransaction = transactionRepository.save(transaction);

        return mapToDto(newTransaction);
    }

    @Override
    public TransactionDTO withdraw(TransactionDTO transactionDTO) {

        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT, ID, transactionDTO.getAccountId()));

        if (account.getBalance().compareTo(transactionDTO.getAmount()) < 0) {
            throw new RequestValidationException(HttpStatus.BAD_REQUEST, INSUFFICIENT_FUNDS);
        }

        account.setBalance(account.getBalance() - transactionDTO.getAmount());
        Transaction transaction = mapToEntity(transactionDTO);

        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.WITHDRAWAL);

        Transaction newTransaction = transactionRepository.save(transaction);
        return mapToDto(newTransaction);
    }

    @Override
    public List<TransactionDTO> getTransactions(Long accountId) {

        List<Transaction> transactions = transactionRepository.findByAccount_Id(accountId);
        List<TransactionDTO> transactionsResponse = transactions.stream().map(this::mapToDto).toList();
        return transactionsResponse;
    }

    private Transaction mapToEntity(TransactionDTO accountDTO) {
        Transaction transaction = modelMapper.map(accountDTO, Transaction.class);
        return transaction;
    }

    private TransactionDTO mapToDto(Transaction account) {
        TransactionDTO transactionDTO = modelMapper.map(account, TransactionDTO.class);
        return transactionDTO;
    }
}
