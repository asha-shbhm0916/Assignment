package com.fudr.customer.dto;

import lombok.Value;

@Value
public class CustomerKycRequest {
    String phoneNumber;
    String email;
    Integer age;
}
