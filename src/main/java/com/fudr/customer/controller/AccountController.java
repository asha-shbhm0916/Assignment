package com.fudr.customer.controller;

import com.fudr.customer.model.Account;
import com.fudr.customer.model.AccountType;
import com.fudr.customer.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-type")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/")
    public ResponseEntity<Account> create(@RequestBody AccountType accountType) {
        log.info("Create Account {}", accountType);
        Account account = accountService.createAccountType(accountType);
        return ResponseEntity.ok(account);
    }

}
