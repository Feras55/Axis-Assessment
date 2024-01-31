package com.axis.assessment.controller;

import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;
import com.axis.assessment.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.axis.assessment.utils.ApplicationConstants.*;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @Operation(
            description = OPEN_ACCOUNT_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = CREATED
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = INTERNAL_SERVER_ERROR
                    ), @ApiResponse(
                    description = "Bad Request",
                    responseCode = BAD_REQUEST
            )
            })
    @PostMapping
    public ResponseEntity<AccountDTO> openAccount(@Valid @RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.openAccount(accountDTO), HttpStatus.CREATED);
    }

    @Operation(description = GET_ACCOUNT_DESCRIPTION,
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
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }

    @Operation(description = GET_BALANCE,
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = SUCCESS
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = INTERNAL_SERVER_ERROR
                    ), @ApiResponse(
                    description = "Not Found",
                    responseCode = NOT_FOUND
            )
            }
    )
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BalanceResponseDTO> getBalance(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.getBalance(accountId), HttpStatus.OK);
    }
}