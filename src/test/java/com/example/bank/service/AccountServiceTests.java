package com.example.bank.service;

import com.example.bank.dto.AccountRequestDTO;
import com.example.bank.dto.AccountResponseDTO;
import com.example.bank.entity.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    void accountCreatedOK () {
        AccountRequestDTO newAccount = new AccountRequestDTO("nat", BigDecimal.valueOf(0));
        String accountId = newAccount.getId();

        Account account = new Account(accountId, "", BigDecimal.valueOf(0), new ArrayList<>());

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponseDTO response = accountService.createAccount(newAccount);

        assertNotNull(response);
        assertEquals(accountId, response.getId());
    }

    @Test
    void getAllAccountsOK () {
        List<Account> accounts = List.of(
                new Account("1", "holder1", BigDecimal.valueOf(0), new ArrayList<>()),
                new Account("2", "holder2", BigDecimal.valueOf(0), new ArrayList<>())
        );

        when(accountRepository.findAll()).thenReturn(accounts);

        var response = accountService.getAllAccounts();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("1", response.get(0).getId());
        assertEquals("2", response.get(1).getId());
    }

    @Test
    void getAllAccountsFail () {
        List<Account> accounts = new ArrayList<>();

        when(accountRepository.findAll()).thenReturn(accounts);

        var response = accountService.getAllAccounts();

        assertNotNull(response);
        assertEquals(0, response.size());
    }

    @Test
    void getAccountByIdOK () {
        String accountId = "1";
        Account account = new Account("1", "holder1", BigDecimal.valueOf(0), new ArrayList<>());

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        var response = accountService.getAccountById(accountId);

        assertNotNull(response);
        assertEquals(accountId, response.getId());
    }

    @Test
    void getAccountByIdFail () {
        String accountId = "2";

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        var exception = assertThrows(NoSuchElementException.class, () -> accountService.getAccountById(accountId));

        assertEquals("No value present", exception.getMessage());
    }
}