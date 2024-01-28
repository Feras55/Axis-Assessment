package com.axis.assessment.service;

import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;

import java.util.List;

public interface AccountService {

    public AccountDTO openAccount(AccountDTO accountDTO);

    public BalanceResponseDTO getBalance(Long accountId);

    public AccountDTO getAccount(Long accountId);

    public List<AccountDTO> getAccounts();
}
