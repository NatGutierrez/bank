package com.bank.account;

import com.bank.Account;
import com.bank.gateway.IAccountRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class CreateUseCaseTests {
    @Test
    void createAccountOK() {
        Account account = new Account("123", "test holder", BigDecimal.valueOf(7846));

        IAccountRepository accountRepositoryGateway = mock(IAccountRepository.class);
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepositoryGateway);

        when(accountRepositoryGateway.createAccount(account)).thenReturn(Mono.just(account));

        Mono<Account> result = createAccountUseCase.apply(account);

        StepVerifier.create(result)
                .expectNext(account)
                .verifyComplete();

        verify(accountRepositoryGateway, times(1)).createAccount(account);
    }
}
