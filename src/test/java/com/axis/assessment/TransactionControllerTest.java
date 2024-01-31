package com.axis.assessment;

import com.axis.assessment.controller.TransactionController;
import com.axis.assessment.payload.TransactionDTO;
import com.axis.assessment.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeposit() {
        TransactionDTO transactionDTO = new TransactionDTO();
        when(transactionService.deposit(transactionDTO)).thenReturn(transactionDTO);

        ResponseEntity<TransactionDTO> responseEntity = transactionController.deposit(transactionDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactionDTO, responseEntity.getBody());
        verify(transactionService, times(1)).deposit(transactionDTO);
    }

    @Test
    void testWithdraw() {
        TransactionDTO transactionDTO = new TransactionDTO();
        when(transactionService.withdraw(transactionDTO)).thenReturn(transactionDTO);

        ResponseEntity<TransactionDTO> responseEntity = transactionController.withdraw(transactionDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactionDTO, responseEntity.getBody());
        verify(transactionService, times(1)).withdraw(transactionDTO);
    }

    @Test
    void testGetTransactions() {
        Long accountId = 1L;
        List<TransactionDTO> transactionDTOList = Collections.singletonList(new TransactionDTO());
        when(transactionService.getTransactions(accountId)).thenReturn(transactionDTOList);

        ResponseEntity<List<TransactionDTO>> responseEntity = transactionController.getTransactions(accountId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactionDTOList, responseEntity.getBody());
        verify(transactionService, times(1)).getTransactions(accountId);
    }
}
