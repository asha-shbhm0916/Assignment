package com.fudr.customer.service;

import com.fudr.customer.model.Account;
import com.fudr.customer.model.AccountType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    private final Map<AccountType, Account> accountTypeAccountMap = new EnumMap<>(AccountType.class);

    @Override
    public Account createAccountType(AccountType accountType) {
        if (accountTypeAccountMap.containsKey(accountType)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Account account = new Account(accountType);
        accountTypeAccountMap.put(accountType, account);
        return account;
    }

    @Override
    public String getAccountTypeId(AccountType accountType) {
        Account account = accountTypeAccountMap.get(accountType);
        if (Objects.isNull(account)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return account.getAccountId();
    }

    @Override
    public Account getAccount(String accountTypeId) {
        return accountTypeAccountMap
                .values()
                .stream()
                .filter(account -> account.getAccountId().equals(accountTypeId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
}
