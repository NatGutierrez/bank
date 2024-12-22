package com.example.bank.mapper;

import com.example.bank.dto.OperationRequestDTO;
import com.example.bank.dto.OperationResponseDTO;
import com.example.bank.entity.Account;
import com.example.bank.entity.Operation;

public class OperationDTOMapper {
    public static OperationResponseDTO toOperationDTO(Operation operation) {
        return new OperationResponseDTO(
                operation.getId(),
                operation.getValue(),
                operation.getType(),
                operation.getAccount().getId()
        );
    }

    public static Operation toOperation(OperationRequestDTO operationDTO) {
        return new Operation(
                operationDTO.getId(),
                operationDTO.getValue(),
                operationDTO.getType(),
                new Account(operationDTO.getAccountId())
        );
    }

    public static Operation toOperation(OperationRequestDTO operationDTO, Account account) {
        return new Operation(
                operationDTO.getId(),
                operationDTO.getValue(),
                operationDTO.getType(),
                account
        );
    }
}