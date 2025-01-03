package com.bank.appservice.operation;

import com.bank.Operation;
import com.bank.gateway.IBusMessage;
import com.bank.gateway.IOperationRepository;
import reactor.core.publisher.Mono;

public class CreateOperationUseCase {
    private final IOperationRepository repository;
    private final IBusMessage busMessage;

    public CreateOperationUseCase(IOperationRepository repository, IBusMessage busMessage) {
        this.repository = repository;
        this.busMessage = busMessage;
    }

    public Mono<Operation> apply(Operation operation) {
        busMessage.sendMsg("Creating " + operation.getType().name() + " operation for $" + operation.getValue() + " on account with id " + operation.getAccount().getId() + ".");
        return repository.createOperation(operation);
    }
}
