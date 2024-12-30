package com.bank.operation;

import com.bank.Account;
import com.bank.Operation;
import com.bank.OperationTypesEnum;
import com.bank.account.FindAccountByIdUseCase;
import com.bank.gateway.IAccountRepository;
import com.bank.gateway.IOperationRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class FindByIdUseCaseTests {
    @Test
    void findOperationByIdOK() {
        String id = "12345";
        Operation operation = new Operation(id, BigDecimal.valueOf(875), OperationTypesEnum.ACCOUNT_DEPOSIT, new Account("123"));

        IOperationRepository operationRepositoryGateway = mock(IOperationRepository.class);
        FindOperationByIdUseCase findOperationByIdUseCase = new FindOperationByIdUseCase(operationRepositoryGateway);

        when(operationRepositoryGateway.findOperationById(id)).thenReturn(Mono.just(operation));

        Mono<Operation> result = findOperationByIdUseCase.apply(id);

        StepVerifier.create(result)
                .expectNext(operation)
                .verifyComplete();

        verify(operationRepositoryGateway, times(1)).findOperationById(id);
    }
}
