package com.axis.assessment;

import com.axis.assessment.entity.Account;
import com.axis.assessment.entity.Transaction;
import com.axis.assessment.enums.TransactionType;
import com.axis.assessment.exception.RequestValidationException;
import com.axis.assessment.payload.TransactionDTO;
import com.axis.assessment.repository.AccountRepository;
import com.axis.assessment.repository.TransactionRepository;
import com.axis.assessment.service.impl.AccountServiceImpl;
import com.axis.assessment.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountServiceImpl accountService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deposit_Success() {
        // Arrange
        Long accountId = 1L;
        BigDecimal initialBalance = BigDecimal.valueOf(100.0);
        BigDecimal depositAmount = BigDecimal.valueOf(50.0);

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(accountId);
        transactionDTO.setAmount(depositAmount);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(modelMapper.map(any(), eq(Transaction.class))).thenReturn(new Transaction());
        when(transactionRepository.save(any())).thenReturn(new Transaction());

        // Act
        TransactionDTO result = transactionService.deposit(transactionDTO);
        // Assert
        assertEquals(initialBalance.add(depositAmount), account.getBalance());
    }

    @Test
    void withdraw_Success() {
        // Arrange
        Long accountId = 1L;
        BigDecimal initialBalance = BigDecimal.valueOf(100.0);
        BigDecimal withdrawalAmount = BigDecimal.valueOf(50.0);

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(accountId);
        transactionDTO.setAmount(withdrawalAmount);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(modelMapper.map(any(), eq(Transaction.class))).thenReturn(new Transaction());
        when(transactionRepository.save(any())).thenReturn(new Transaction());

        // Act
        TransactionDTO result = transactionService.withdraw(transactionDTO);

        // Assert
        assertEquals(initialBalance.subtract(withdrawalAmount), account.getBalance());
    }

    @Test
    void withdraw_Failure_InsufficientFunds() {
        // Arrange
        Long accountId = 1L;
        BigDecimal initialBalance = BigDecimal.valueOf(100.0);
        BigDecimal withdrawalAmount = BigDecimal.valueOf(50.0);

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(accountId);
        transactionDTO.setAmount(withdrawalAmount);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act & Assert
        assertThrows(RequestValidationException.class, () -> transactionService.withdraw(transactionDTO));
    }

    @Test
    void getTransactions_Success() {
        // Arrange
        Long accountId = 1L;

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccount(new Account());

        when(transactionRepository.findByAccount_Id(accountId)).thenReturn(Collections.singletonList(transaction));
        when(modelMapper.map(any(), eq(TransactionDTO.class))).thenReturn(new TransactionDTO());

        // Act
        List<TransactionDTO> result = transactionService.getTransactions(accountId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

}
