package com.example.bank.mapper;

import com.example.bank.dto.AccountRequestDTO;
import com.example.bank.dto.AccountResponseDTO;
import com.example.bank.entity.Account;

public class AccountDTOMapper {
    public static AccountResponseDTO toAccountDTO(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getHolder(),
                account.getBalance(),
                account.getOperations().stream().map(OperationDTOMapper::toOperationDTO).toList()
        );
    }

    public static Account toAccount(AccountRequestDTO accountDTO) {
        return new Account(
                accountDTO.getId(),
                accountDTO.getHolder(),
                accountDTO.getBalance(),
                accountDTO.getOperations().stream().map(OperationDTOMapper::toOperation).toList()
        );
    }
}