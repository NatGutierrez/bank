package com.example.bank.service;

import com.example.bank.dto.OperationRequestDTO;
import com.example.bank.dto.OperationResponseDTO;
import com.example.bank.entity.Operation;

import java.util.List;

public interface OperationService {
    List<OperationResponseDTO> getAllOperations();
    OperationResponseDTO getOperationById(String id);
    OperationResponseDTO createOperation(OperationRequestDTO operationDTO);
    private Operation applyOperation(Operation operation) { return operation; };
    void revertOperation(int id);
}