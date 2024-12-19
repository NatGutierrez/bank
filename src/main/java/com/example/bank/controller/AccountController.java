package com.example.bank.controller;

import com.example.bank.dto.AccountDTO;
import com.example.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Account endpoints", description = "")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Get Accounts", description = "List all bank accounts.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully obtained all accounts."),
            @ApiResponse(responseCode = "204", description = "No accounts to get.")
    })
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        var response = accountService.getAllAccounts();
        return response.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : // 204
                ResponseEntity.status(HttpStatus.OK).body(response); // 200
    }

    @Operation(summary = "Get account by id", description = "Find a single bank account by its id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully obtained account."),
            @ApiResponse(responseCode = "404", description = "Account not found.")
    })
    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
        /*return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok*/
    }

    @Operation(summary = "Create new account.", description = "Create a new bank account.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created account.")
    })
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        var response = accountService.createAccount(accountDTO);
        return response != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(response) : // 201
                ResponseEntity.status(HttpStatus.NOT_MODIFIED).build(); // 304
    }
}