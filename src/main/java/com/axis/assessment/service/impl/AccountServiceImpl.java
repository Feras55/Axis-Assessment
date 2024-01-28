package com.axis.assessment.service.impl;


import com.axis.assessment.entity.Account;
import com.axis.assessment.exception.AccountValidationException;
import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;
import com.axis.assessment.repository.AccountRepository;
import com.axis.assessment.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private ModelMapper modelMapper;


    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDTO openAccount(AccountDTO accountDTO) {

        accountRepository.findByUsername(accountDTO.getUsername()).ifPresent(account -> {
            throw new AccountValidationException("Account already exists");
        });

        Account account = mapToEntity(accountDTO);
        account.setBalance(0.0);


        return mapToDto(accountRepository.save(account));
    }

    @Override
    public BalanceResponseDTO getBalance(Long accountId) {
        Double balance = accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new AccountValidationException("Account not found"));

        return new BalanceResponseDTO(balance);
    }

    @Override
    public AccountDTO getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountValidationException("Account not found"));
        return mapToDto(account);
    }

    @Override
    public List<AccountDTO> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = accounts.stream().map(account -> mapToDto(account)).collect(Collectors.toList());
        return accountDTOS;
    }


    private Account mapToEntity(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        return account;
    }

    private AccountDTO mapToDto(Account account) {
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        return accountDTO;
    }

}
