package com.example.bank.mapper;

import com.example.bank.dto.OperationTypeDTO;
import com.example.bank.entity.OperationType;

public class OperationTypeDTOMapper {
    public static OperationTypeDTO toOperationTypeDTO(OperationType operationType) {
        return new OperationTypeDTO(
                operationType.getId(),
                operationType.getName(),
                operationType.getCost(),
                operationType.getAction()
        );
    }

    public static OperationType toOperationType(OperationTypeDTO operationTypeDTO) {
        return new OperationType(
                operationTypeDTO.getId(),
                operationTypeDTO.getName(),
                operationTypeDTO.getCost(),
                operationTypeDTO.getAction()
        );
    }
}
