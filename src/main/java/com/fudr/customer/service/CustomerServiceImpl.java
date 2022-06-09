package com.fudr.customer.service;

import com.fudr.customer.dto.CustomerKycRequest;
import com.fudr.customer.model.Customer;
import com.fudr.customer.model.CustomerAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    @Autowired
    @Lazy
    CustomerAccountService customerAccountService;

    @Override
    public String createCustomer(String name) {
        if (Objects.isNull(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Customer customer = Customer.createCustomer(name);
        customers.put(customer.getCustomerId(), customer);
        return customer.getCustomerId();
    }

    @Override
    public Boolean updateCustomer(String customerId, CustomerKycRequest customerKycRequest) {
        Customer customer = customers.get(customerId);
        if (Objects.isNull(customer)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No customer found");
        }
        customer.setAge(customerKycRequest.getAge());
        customer.setPhoneNumber(customerKycRequest.getPhoneNumber());
        customer.setEmail(customerKycRequest.getEmail());
        Customer updatedCustomer = customers.put(customerId, customer);
        return Objects.nonNull(updatedCustomer);
    }

    @Override
    public Customer getCustomer(String customerId) {
        Customer customer = customers.get(customerId);
        if (Objects.isNull(customer)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return customer;
    }

    @Override
    public Boolean deleteCustomer(String customerId) {
        Optional<CustomerAccount> customerAccount = customerAccountService.getCustomerAccountByCustomerId(customerId);
        if (customerAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Customer customer = customers.remove(customerId);
        return Objects.nonNull(customer);
    }
}
