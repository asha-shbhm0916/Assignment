package com.fudr.customer.model;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class Account implements Identifiable {
    String accountId;
    AccountType accountType;

    public Account(AccountType accountType) {
        this.accountId = getId();
        this.accountType = accountType;
    }
}
