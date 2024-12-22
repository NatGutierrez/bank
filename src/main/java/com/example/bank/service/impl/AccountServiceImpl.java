package com.example.bank.service.impl;

import com.example.bank.dto.AccountRequestDTO;
import com.example.bank.dto.AccountResponseDTO;
import com.example.bank.mapper.AccountDTOMapper;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(AccountDTOMapper::toAccountDTO).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(String id) {
        return AccountDTOMapper.toAccountDTO(accountRepository.findById(id).orElseThrow());
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountDTO) {
        var acc = AccountDTOMapper.toAccount(new AccountRequestDTO(accountDTO.getHolder(), accountDTO.getBalance()));
        return AccountDTOMapper.toAccountDTO(accountRepository.save(acc));
    }
}