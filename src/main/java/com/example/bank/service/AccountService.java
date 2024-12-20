package com.example.bank.service;

import com.example.bank.dto.AccountRequestDTO;
import com.example.bank.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    List<AccountResponseDTO> getAllAccounts();
    AccountResponseDTO getAccountById(String id);
    AccountResponseDTO createAccount(AccountRequestDTO accountDTO);
}