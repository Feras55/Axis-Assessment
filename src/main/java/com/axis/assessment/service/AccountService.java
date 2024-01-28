package com.axis.assessment.service;

import com.axis.assessment.payload.AccountDTO;
import com.axis.assessment.payload.response.BalanceResponseDTO;

import java.util.List;

public interface AccountService {

    AccountDTO openAccount(AccountDTO accountDTO);

    BalanceResponseDTO getBalance(Long accountId);

    AccountDTO getAccount(Long accountId);

    List<AccountDTO> getAccounts();
}
