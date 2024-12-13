package com.example.bank.service.impl;

import com.example.bank.dto.OperationDTO;
import com.example.bank.mapper.DTOMapper;
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
        return operationRepository.findAll().stream().map(DTOMapper::toOperationDTO).collect(Collectors.toList());
    }

    @Override
    public OperationDTO getOperationById(int id) {
        return DTOMapper.toOperationDTO(operationRepository.findById(String.valueOf(id)).get());
    }

    @Override
    public OperationDTO createOperation(OperationDTO operationDTO) {
        /*TODO:
        * Get type -> get cost
        * Get account -> get current balance
        * set balance +- value +- cost
        * return op
        * */
        return DTOMapper.toOperationDTO(operationRepository.save(DTOMapper.toOperation(operationDTO)));
    }
}
