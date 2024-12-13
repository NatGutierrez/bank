package com.example.bank.service;

import com.example.bank.dto.OperationTypeDTO;

import java.util.List;

public interface OperationTypeService {
    List<OperationTypeDTO> getAllOperationTypes();
    OperationTypeDTO getOperationTypeById(int id);
    OperationTypeDTO createOperationType(OperationTypeDTO operationTypeDTO);
}
