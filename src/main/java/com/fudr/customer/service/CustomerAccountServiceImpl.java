package com.fudr.customer.service;

import com.fudr.customer.model.Account;
import com.fudr.customer.model.AccountType;
import com.fudr.customer.model.Customer;
import com.fudr.customer.model.CustomerAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final Map<String, CustomerAccount> customerAccountMap = new ConcurrentHashMap<>();
    private final CustomerService customerService;
    private final AccountService accountService;

    @Override
    public String createCustomerAccount(String customerId, AccountType accountType) {
        Customer customer = customerService.getCustomer(customerId);
        if (Objects.isNull(customer)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No customer found");
        }
        String accountTypeId = accountService.getAccountTypeId(accountType);
        boolean isAlreadyExist = customerAccountMap
                .values()
                .stream()
                .anyMatch(customerAccount -> customerAccount.getAccountTypeId().equals(accountTypeId) && customerAccount.getCustomerId().equals(customerId));
        if (isAlreadyExist) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        CustomerAccount customerAccount = new CustomerAccount(customerId, accountTypeId);
        customerAccountMap.put(customerAccount.getAccountId(), customerAccount);
        return customerAccount.getAccountId();
    }

    @Override
    public Boolean updateBalance(String customerAccountId, double balance) {
        CustomerAccount customerAccount = customerAccountMap.get(customerAccountId);
        if (Objects.isNull(customerAccount)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        Account account = accountService.getAccount(customerAccount.getAccountTypeId());
        if (account.getAccountType() == AccountType.LOAN) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        customerAccount.updateBalance(balance);
        return true;
    }

    @Override
    public Double fetchBalance(String customerAccountId) {
        CustomerAccount customerAccount = customerAccountMap.get(customerAccountId);
        if (Objects.isNull(customerAccount)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        Account account = accountService.getAccount(customerAccount.getAccountTypeId());
        if (account.getAccountType() == AccountType.LOAN) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return customerAccount.getBalance();
    }

    @Override
    public Optional<CustomerAccount> getCustomerAccountByCustomerId(String customerId) {
        return customerAccountMap.values()
                .stream()
                .filter(customerAccount -> customerAccount.getCustomerId().equals(customerId))
                .findFirst();
    }
}
