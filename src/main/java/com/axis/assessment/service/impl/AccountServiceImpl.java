package com.axis.assessment.service.impl;


import com.axis.assessment.entity.Account;
import com.axis.assessment.exception.AlreadyExistsException;
import com.axis.assessment.exception.ResourceNotFoundException;
import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;
import com.axis.assessment.repository.AccountRepository;
import com.axis.assessment.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.axis.assessment.utils.ApplicationConstants.*;

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
            throw new AlreadyExistsException(ACCOUNT, USERNAME, accountDTO.getUsername());
        });

        Account account = mapToEntity(accountDTO);
        account.setBalance(BigDecimal.ZERO);


        return mapToDto(accountRepository.save(account));
    }

    @Override
    public BalanceResponseDTO getBalance(Long accountId) {
        BigDecimal balance = accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT, ID, accountId));

        return new BalanceResponseDTO(balance);
    }

    @Override
    public AccountDTO getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT, ID, accountId));
        return mapToDto(account);
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
