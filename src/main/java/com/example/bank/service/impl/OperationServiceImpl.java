package com.example.bank.service.impl;

import com.example.bank.dto.OperationRequestDTO;
import com.example.bank.dto.OperationResponseDTO;
import com.example.bank.entity.Account;
import com.example.bank.entity.Operation;
import com.example.bank.mapper.OperationDTOMapper;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.OperationRepository;
import com.example.bank.service.OperationService;
import com.example.bank.utils.operations.OperationType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;

    private final AccountRepository accountRepository;

    public OperationServiceImpl(OperationRepository operationRepository, AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<OperationResponseDTO> getAllOperations() {
        return operationRepository.findAll().stream().map(OperationDTOMapper::toOperationDTO).collect(Collectors.toList());
    }

    @Override
    public OperationResponseDTO getOperationById(String id) {
        return OperationDTOMapper.toOperationDTO(operationRepository.findById(id).orElseThrow());
    }

    @Override
    public OperationResponseDTO createOperation(OperationRequestDTO operationDTO) {
        Optional<Account> account = accountRepository.getAccountById(operationDTO.getAccountId());
        Operation op = applyOperation(OperationDTOMapper.toOperation(operationDTO, account.orElseThrow()));
        return OperationDTOMapper.toOperationDTO(operationRepository.save(op));
    }

    private Operation applyOperation(Operation op) {
        Account account = op.getAccount();
        OperationType opType = Operation.getOperationTypeObj(op.getType());
        BigDecimal cost = opType.cost();
        BigDecimal value = op.getValue();

        BigDecimal operationTotal;
        switch (opType.action()) {
            case DEBIT -> operationTotal = value.add(cost).negate();
            case CREDIT -> operationTotal = value.subtract(cost);
            default -> operationTotal = BigDecimal.ZERO;
        }

        account.setBalance(account.getBalance().add(operationTotal));

        return op;
    }

    @Override
    public void revertOperation(int id) {}
}