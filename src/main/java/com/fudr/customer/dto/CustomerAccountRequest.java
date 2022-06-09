package com.fudr.customer.dto;

import com.fudr.customer.model.AccountType;
import lombok.Value;

@Value
public class CustomerAccountRequest {

    String customerId;
    AccountType accountType;
}
