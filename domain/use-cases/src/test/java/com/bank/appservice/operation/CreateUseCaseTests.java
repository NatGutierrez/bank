package com.bank.appservice.operation;

import com.bank.Account;
import com.bank.Operation;
import com.bank.OperationTypesEnum;
import com.bank.appservice.operation.CreateOperationUseCase;
import com.bank.gateway.IBusMessage;
import com.bank.gateway.ILogRepository;
import com.bank.gateway.IOperationRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CreateUseCaseTests {
    @Test
    void createOperationOK() {
        String id = UUID.randomUUID().toString();
        Operation operation = new Operation(id, BigDecimal.valueOf(875), OperationTypesEnum.ACCOUNT_DEPOSIT, new Account("123"));

        IOperationRepository operationRepositoryGateway = mock(IOperationRepository.class);
        IBusMessage busMessage = mock(IBusMessage.class);

        CreateOperationUseCase createOperationUseCase = new CreateOperationUseCase(operationRepositoryGateway, busMessage);

        when(operationRepositoryGateway.createOperation(operation)).thenReturn(Mono.just(operation));

        Mono<Operation> result = createOperationUseCase.apply(operation);

        StepVerifier.create(result)
                .expectNext(operation)
                .verifyComplete();

        verify(operationRepositoryGateway, times(1)).createOperation(operation);
    }
}
