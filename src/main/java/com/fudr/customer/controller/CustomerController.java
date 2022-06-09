package com.fudr.customer.controller;

import com.fudr.customer.dto.CustomerKycRequest;
import com.fudr.customer.model.Customer;
import com.fudr.customer.service.CustomerAccountService;
import com.fudr.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerAccountService customerAccountService;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody String name) {
        log.info("Create Customer {}", name);
        String customerId = customerService.createCustomer(name);
        return ResponseEntity.ok(customerId);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Boolean> update(@PathVariable String customerId, @RequestBody CustomerKycRequest customerKycRequest) {
        log.info("Update Customer {}", customerId);
        Boolean updated = customerService.updateCustomer(customerId, customerKycRequest);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> get(@PathVariable String customerId) {
        log.info("Get Customer {}", customerId);
        Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Boolean> delete(@PathVariable String customerId) {
        log.info("Delete Customer {}", customerId);
        Boolean deleteCustomer = customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(deleteCustomer);
    }
}
