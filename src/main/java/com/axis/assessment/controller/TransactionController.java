package com.axis.assessment.controller;


import com.axis.assessment.payload.TransactionDTO;
import com.axis.assessment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody TransactionDTO transactionDTO) {
        return new ResponseEntity<>(transactionService.deposit(transactionDTO), HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(@RequestBody TransactionDTO transactionDTO) {
        return new ResponseEntity<>(transactionService.withdraw(transactionDTO), HttpStatus.OK);

    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getTransactions(accountId), HttpStatus.OK);
    }
}
