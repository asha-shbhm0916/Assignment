package com.fudr.customer.controller;

import com.fudr.customer.dto.CustomerAccountRequest;
import com.fudr.customer.service.CustomerAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-account")
@RequiredArgsConstructor
@Slf4j
public class CustomerAccountController {

    private final CustomerAccountService customerAccountService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody CustomerAccountRequest customerAccountRequest) {
        log.info("Create Customer Account");
        String customerAccountId = customerAccountService.createCustomerAccount(customerAccountRequest.getCustomerId(), customerAccountRequest.getAccountType());
        return ResponseEntity.ok(customerAccountId);
    }

    @PutMapping("/balance/{customerAccountId}/update")
    public ResponseEntity<Boolean> updatedBalance(@PathVariable String customerAccountId, @RequestBody Double balance) {
        log.info("Update Account balance {}", customerAccountId);
        Boolean updated = customerAccountService.updateBalance(customerAccountId, balance);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/balance/{customerAccountId}")
    public ResponseEntity<Double> getAccountBalance(@PathVariable String customerAccountId) {
        log.info("Get Customer Account Balance  {}", customerAccountId);
        Double balance = customerAccountService.fetchBalance(customerAccountId);
        return ResponseEntity.ok(balance);
    }

}
