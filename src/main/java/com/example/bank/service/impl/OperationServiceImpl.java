package com.example.bank.service.impl;

import com.example.bank.dto.OperationDTO;
import com.example.bank.mapper.OperationDTOMapper;
import com.example.bank.repository.OperationRepository;
import com.example.bank.service.OperationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public List<OperationDTO> getAllOperations() {
        return operationRepository.findAll().stream().map(OperationDTOMapper::toOperationDTO).collect(Collectors.toList());
    }

    @Override
    public OperationDTO getOperationById(int id) {
        return OperationDTOMapper.toOperationDTO(operationRepository.findById(String.valueOf(id)).get());
    }

    @Override
    public OperationDTO createOperation(OperationDTO operationDTO) {
        /*TODO:
        * Get type -> get cost
        * Get account -> get current balance
        * set balance +- value +- cost
        * return op
        * */
        return OperationDTOMapper.toOperationDTO(operationRepository.save(OperationDTOMapper.toOperation(operationDTO)));
    }
}
