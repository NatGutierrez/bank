package com.example.bank.controller;

import com.example.bank.dto.AccountDTO;
import com.example.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        var response = accountService.getAllAccounts();
        return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id);
        /*return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok*/
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        var response = accountService.createAccount(accountDTO);
        return response != null ?
                ResponseEntity.status(201).body(response) : // created
                ResponseEntity.status(304).build(); // not modified
    }
}
