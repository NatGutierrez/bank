package com.example.bank.mapper;

import com.example.bank.dto.OperationDTO;
import com.example.bank.entity.Operation;

public class OperationDTOMapper {
    public static OperationDTO toOperationDTO(Operation operation) {
        return new OperationDTO(
                operation.getId(),
                operation.getValue(),
                operation.getType(),
                AccountDTOMapper.toAccountDTO(operation.getAccount())
        );
    }

    public static Operation toOperation(OperationDTO operationDTO) {
        return new Operation(
                operationDTO.getId(),
                operationDTO.getValue(),
                operationDTO.getOperationType(),
                AccountDTOMapper.toAccount(operationDTO.getAccount())
        );
    }
}
