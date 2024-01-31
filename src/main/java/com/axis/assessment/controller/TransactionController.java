package com.axis.assessment.controller;


import com.axis.assessment.payload.TransactionDTO;
import com.axis.assessment.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.axis.assessment.utils.ApplicationConstants.*;
import static com.axis.assessment.utils.ApplicationConstants.BAD_REQUEST;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(
            description = DEPOSIT,
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = SUCCESS
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = INTERNAL_SERVER_ERROR
                    ), @ApiResponse(
                    description = "Bad Request",
                    responseCode = BAD_REQUEST
            )
            })
    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody TransactionDTO transactionDTO) {
        return new ResponseEntity<>(transactionService.deposit(transactionDTO), HttpStatus.OK);
    }

    @Operation(
            description = WITHDRAW,
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = SUCCESS
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = INTERNAL_SERVER_ERROR
                    ), @ApiResponse(
                    description = "Bad Request",
                    responseCode = BAD_REQUEST
            )
            })
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(@RequestBody TransactionDTO transactionDTO) {
        return new ResponseEntity<>(transactionService.withdraw(transactionDTO), HttpStatus.OK);

    }
    @Operation(
            description = GET_TRANSACTIONS,
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = SUCCESS
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = INTERNAL_SERVER_ERROR
                    ),
                    @ApiResponse(
                    description = "Not Found",
                    responseCode = NOT_FOUND
            )
            })
    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getTransactions(accountId), HttpStatus.OK);
    }
}
