package com.example.bank.mapper;

import com.example.bank.dto.AccountDTO;
import com.example.bank.entity.Account;

import java.util.ArrayList;

public class AccountDTOMapper {
    public static AccountDTO toAccountDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getHolder(),
                account.getBalance(),
                new ArrayList<>()
                //account.getOperations().stream().map(DTOMapper::toOperationDTO).collect(Collectors.toList())
        );
    }

    public static Account toAccount(AccountDTO accountDTO) {
        return new Account(
                accountDTO.getId(),
                accountDTO.getHolder(),
                accountDTO.getBalance(),
                new ArrayList<>()
                //accountDTO.getOperations().stream().map(DTOMapper::toOperation).collect(Collectors.toList())
        );
    }
}
