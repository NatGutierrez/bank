package com.example.bank.controller;

import com.example.bank.dto.AccountDTO;
import com.example.bank.service.AccountService;
import org.springframework.http.HttpStatus;
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
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : // 204
                ResponseEntity.status(HttpStatus.OK).body(response); // 200
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
                ResponseEntity.status(HttpStatus.CREATED).body(response) : // 201
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).build(); // 304
    }
}
