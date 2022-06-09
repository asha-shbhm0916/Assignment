package com.fudr.customer.service;

import com.fudr.customer.model.Account;
import com.fudr.customer.model.AccountType;

public interface AccountService {
    Account createAccountType(AccountType accountType);
    String getAccountTypeId(AccountType accountType);

    Account getAccount(String accountTypeId);
}
