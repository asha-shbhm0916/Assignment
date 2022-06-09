package com.fudr.customer.service;

import com.fudr.customer.model.AccountType;
import com.fudr.customer.model.CustomerAccount;

import java.util.Optional;

public interface CustomerAccountService {
    String createCustomerAccount(String customerId, AccountType accountType);
    Boolean updateBalance(String customerAccountId, double balance);

    Double fetchBalance(String customerAccountId);

    Optional<CustomerAccount> getCustomerAccountByCustomerId(String customerId);
}
