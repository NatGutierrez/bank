package com.example.bank.service.impl;

import com.example.bank.dto.OperationTypeDTO;
import com.example.bank.mapper.OperationTypeDTOMapper;
import com.example.bank.repository.OperationTypeRepository;
import com.example.bank.service.OperationTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationTypeServiceImpl implements OperationTypeService {
    private final OperationTypeRepository operationTypeRepository;

    public OperationTypeServiceImpl(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public List<OperationTypeDTO> getAllOperationTypes() {
        return operationTypeRepository.findAll().stream().map(OperationTypeDTOMapper::toOperationTypeDTO).collect(Collectors.toList());
    }

    @Override
    public OperationTypeDTO getOperationTypeById(int id) {
        return OperationTypeDTOMapper.toOperationTypeDTO(operationTypeRepository.findById(String.valueOf(id)).get());
    }

    @Override
    public OperationTypeDTO createOperationType(OperationTypeDTO operationTypeDTO) {
        return OperationTypeDTOMapper.toOperationTypeDTO(operationTypeRepository.save(OperationTypeDTOMapper.toOperationType(operationTypeDTO)));
    }
}
