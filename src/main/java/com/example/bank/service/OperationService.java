package com.example.bank.service;

import com.example.bank.dto.OperationDTO;

import java.util.List;

public interface OperationService {
    List<OperationDTO> getAllOperations();
    OperationDTO getOperationById(int id);
    OperationDTO createOperation(OperationDTO operationDTO);
    // void deleteOperation(int id); // no se debería, en todo caso hay que revertirla con una nueva
}
