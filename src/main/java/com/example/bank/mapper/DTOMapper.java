package com.example.bank.mapper;

import com.example.bank.dto.AccountDTO;
import com.example.bank.dto.OperationDTO;
import com.example.bank.dto.OperationTypeDTO;
import com.example.bank.entity.Account;
import com.example.bank.entity.Operation;
import com.example.bank.entity.OperationType;

import java.util.ArrayList;

public class DTOMapper {
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

    public static OperationDTO toOperationDTO(Operation operation) {
        return new OperationDTO(
                operation.getId(),
                operation.getValue(),
                DTOMapper.toOperationTypeDTO(operation.getType()),
                toAccountDTO(operation.getAccount())
        );
    }

    public static Operation toOperation(OperationDTO operationDTO) {
        return new Operation(
                operationDTO.getId(),
                operationDTO.getValue(),
                DTOMapper.toOperationType(operationDTO.getOperationType()),
                toAccount(operationDTO.getAccount())
        );
    }

    public static OperationTypeDTO toOperationTypeDTO(OperationType operationType) {
        return new OperationTypeDTO(
                operationType.getId(),
                operationType.getName(),
                operationType.getCost()
        );
    }

    public static OperationType toOperationType(OperationTypeDTO operationTypeDTO) {
        return new OperationType(
                operationTypeDTO.getId(),
                operationTypeDTO.getName(),
                operationTypeDTO.getCost()
        );
    }
}
