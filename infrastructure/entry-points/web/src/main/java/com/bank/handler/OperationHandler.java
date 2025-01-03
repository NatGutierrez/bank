package com.bank.handler;

import com.bank.Account;
import com.bank.Operation;
import com.bank.OperationType;
import com.bank.appservice.account.FindAccountByIdUseCase;
import com.bank.data.OperationRequestDTO;
import com.bank.data.OperationResponseDTO;
import com.bank.mapper.OperationMapper;
import com.bank.appservice.operation.CreateOperationUseCase;
import com.bank.appservice.operation.FindAllOperationUseCase;
import com.bank.appservice.operation.FindOperationByIdUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Component
@Validated
public class OperationHandler {
    private final FindAllOperationUseCase findAllOperationUseCase;

    private final FindOperationByIdUseCase findOperationByIdUseCase;

    private final CreateOperationUseCase createOperationUseCase;

    private final FindAccountByIdUseCase findAccountByIdUseCase;

    public OperationHandler(
            FindAllOperationUseCase findAllOperationUseCase,
            FindOperationByIdUseCase findOperationByIdUseCase,
            CreateOperationUseCase createOperationUseCase,
            FindAccountByIdUseCase findAccountByIdUseCase
    ) {
        this.findAllOperationUseCase = findAllOperationUseCase;
        this.findOperationByIdUseCase = findOperationByIdUseCase;
        this.createOperationUseCase = createOperationUseCase;
        this.findAccountByIdUseCase = findAccountByIdUseCase;
    }

    public Flux<OperationResponseDTO> findAllOperations() {
        return findAllOperationUseCase.apply()
                .map(OperationMapper::toDTO)
                .switchIfEmpty(Mono.error(new NoSuchElementException("No operations to list.")));
    }

    public Mono<OperationResponseDTO> findOperationById(String id) {
        if (id == null || id.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Id cannot be null."));
        }
        return findOperationByIdUseCase.apply(id)
                .map(OperationMapper::toDTO)
                .switchIfEmpty(Mono.error(new NoSuchElementException("Operation with id " + id + " does not exist.")));
    }

    public Mono<OperationResponseDTO> createOperation(@Valid OperationRequestDTO operationRequestDTO) {
        return findAccountByIdUseCase.apply(operationRequestDTO.getAccountId())
                .flatMap(acc -> {
                    var op = applyOperation(OperationMapper.toEntity(operationRequestDTO, acc));
                    return createOperationUseCase.apply(op);
                })
                .map(OperationMapper::toDTO)
                .switchIfEmpty(Mono.error(new NoSuchElementException("Account not found.")));
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
}
