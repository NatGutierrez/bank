package com.example.bank.controller;

import com.example.bank.dto.AccountRequestDTO;
import com.example.bank.dto.AccountResponseDTO;
import com.example.bank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccountService accountService;

    private AccountRequestDTO accountRequest;
    private AccountResponseDTO accountResponse;

    @BeforeEach
    void setUp() {
        accountRequest = new AccountRequestDTO("holder", BigDecimal.ZERO);
        accountResponse = new AccountResponseDTO("123", "holder", BigDecimal.ZERO, new ArrayList<>());
    }

    @Test
    void createAccount_ReturnsCreated() throws Exception {
        when(accountService.createAccount(any(AccountRequestDTO.class))).thenReturn(accountResponse);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.holder").value("holder"))
                .andExpect(jsonPath("$.balance").value(0.0));

        verify(accountService, times(1)).createAccount(any(AccountRequestDTO.class));
    }

    @Test
    void createAccount_ReturnsBadRequest() throws Exception {
        when(accountService.createAccount(any(AccountRequestDTO.class)))
                .thenThrow(new IllegalArgumentException("Request body not valid."));

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isBadRequest());

        verify(accountService, times(1)).createAccount(any(AccountRequestDTO.class));
    }

    @Test
    void getAllAccounts_ReturnsAccountsList() throws Exception {
        List<AccountResponseDTO> accountList = List.of(accountResponse);

        when(accountService.getAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("123"))
                .andExpect(jsonPath("$[0].holder").value("holder"))
                .andExpect(jsonPath("$[0].balance").value(0.0));

        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void getAllAccounts_ReturnsAccountsEmpty() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isNoContent());

        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void getById_ReturnsAccount() throws Exception {
        when(accountService.getAccountById("123")).thenReturn(accountResponse);

        mockMvc.perform(get("/accounts/{id}", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.holder").value("holder"))
                .andExpect(jsonPath("$.balance").value(0.0));

        verify(accountService, times(1)).getAccountById("123");
    }

    @Test
    void getById_ReturnsNotFound() throws Exception {
        when(accountService.getAccountById("123"))
                .thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/accounts/{id}", "123"))
                .andExpect(status().isNotFound());

        verify(accountService, times(1)).getAccountById("123");
    }
}
