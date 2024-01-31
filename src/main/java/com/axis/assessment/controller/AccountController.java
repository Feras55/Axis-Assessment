package com.axis.assessment.controller;

import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;
import com.axis.assessment.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.getBalance(accountId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> openAccount(@Valid @RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.openAccount(accountDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }

}