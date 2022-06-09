package com.fudr.customer.service;

import com.fudr.customer.dto.CustomerKycRequest;
import com.fudr.customer.model.Customer;

public interface CustomerService {
    String createCustomer(String name);
    Boolean updateCustomer(String customerId, CustomerKycRequest customerKycRequest);
    Customer getCustomer(String customerId);
    Boolean deleteCustomer(String customerId);
}
