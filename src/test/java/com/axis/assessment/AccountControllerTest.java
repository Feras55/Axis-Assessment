package com.axis.assessment;


import com.axis.assessment.controller.AccountController;
import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;
import com.axis.assessment.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void openAccount_ReturnsCreatedResponse() {
        // Given
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(1L);
        when(accountService.openAccount(any(AccountDTO.class))).thenReturn(accountDTO);

        // When
        ResponseEntity<AccountDTO> responseEntity = accountController.openAccount(accountDTO);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(accountDTO, responseEntity.getBody());
    }

    @Test
    void getAccountById_ReturnsOkResponse() {
        // Given
        Long accountId = 1L;
        String username = "feras";
        String password = "passwword";
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountId);
        when(accountService.getAccount(accountId)).thenReturn(accountDTO);

        // When
        ResponseEntity<AccountDTO> responseEntity = accountController.getAccountById(accountId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountDTO, responseEntity.getBody());
    }

    @Test
    void getBalance_ReturnsOkResponse() {
        // Given
        Long accountId = 1L;
        BalanceResponseDTO balanceResponseDTO = new BalanceResponseDTO();
        when(accountService.getBalance(accountId)).thenReturn(balanceResponseDTO);


        // When
        ResponseEntity<BalanceResponseDTO> responseEntity = accountController.getBalance(accountId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(balanceResponseDTO, responseEntity.getBody());
    }
}

