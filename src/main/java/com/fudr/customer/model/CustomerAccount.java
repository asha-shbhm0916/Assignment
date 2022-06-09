package com.fudr.customer.model;

import lombok.Data;

@Data
public class CustomerAccount implements Identifiable {
    final String accountId;
    final String customerId;
    final String accountTypeId;
    Double balance;

    public CustomerAccount(String customerId, String accountTypeId) {
        this.accountId = getId();
        this.customerId = customerId;
        this.accountTypeId = accountTypeId;
        this.balance = 0D;
    }

    public void updateBalance(double balance) {
        this.balance = balance;
    }
}
