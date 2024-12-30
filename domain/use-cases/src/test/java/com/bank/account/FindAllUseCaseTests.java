package com.bank.account;

import com.bank.Account;
import com.bank.gateway.IAccountRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class FindAllUseCaseTests {
    @Test
    void findAllAccountsOK() {
        Account account = new Account("123", "test holder", BigDecimal.valueOf(7846));

        IAccountRepository accountRepositoryGateway = mock(IAccountRepository.class);
        FindAllAccountsUseCase findAllAccountsUseCase = new FindAllAccountsUseCase(accountRepositoryGateway);

        when(accountRepositoryGateway.findAllAccounts()).thenReturn(Flux.just(account));

        Flux<Account> result = findAllAccountsUseCase.apply();

        StepVerifier.create(result)
                .expectNext(account)
                .verifyComplete();

        verify(accountRepositoryGateway, times(1)).findAllAccounts();
    }
}
