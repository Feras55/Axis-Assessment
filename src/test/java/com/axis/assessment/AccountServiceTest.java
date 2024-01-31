package com.axis.assessment;

import com.axis.assessment.entity.Account;
import com.axis.assessment.exception.AlreadyExistsException;
import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;
import com.axis.assessment.repository.AccountRepository;
import com.axis.assessment.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void openAccount_Success() {
        // Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername("testUser");
        accountDTO.setPassword("testPassword");

        Account account = new Account();
        account.setUsername("testUser");
        account.setPassword("testPassword");

        when(accountRepository.findByUsername(accountDTO.getUsername())).thenReturn(Optional.empty());
        when(accountRepository.save(any())).thenReturn(account);
        when(modelMapper.map(any(), eq(Account.class))).thenReturn(account);
        when(modelMapper.map(any(), eq(AccountDTO.class))).thenReturn(accountDTO);

        // Act
        AccountDTO result = accountService.openAccount(accountDTO);

        // Assert
        assertNotNull(result);
        assertEquals(accountDTO.getUsername(), result.getUsername());
        // Add more assertions based on your requirements
    }

    @Test
    void openAccount_Failure_AlreadyExists() {
        // Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername("existingUser");

        Account existingAccount = new Account();
        existingAccount.setUsername("existingUser");

        when(accountRepository.findByUsername(accountDTO.getUsername())).thenReturn(Optional.of(existingAccount));

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> accountService.openAccount(accountDTO));
    }

    @Test
    void getBalance_Success() {
        // Arrange
        Long accountId = 1L;
        Double balanceValue = 100.0;

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(balanceValue);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act
        BalanceResponseDTO result = accountService.getBalance(accountId);

        // Assert
        assertNotNull(result);
        assertEquals(balanceValue, result.getBalance());
    }

}
