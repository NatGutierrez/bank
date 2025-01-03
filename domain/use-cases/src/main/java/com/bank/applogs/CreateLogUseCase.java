package com.bank.applogs;

import com.bank.Log;
import com.bank.gateway.ILogRepository;
import reactor.core.publisher.Mono;

public class CreateLogUseCase {
    private final ILogRepository logRepository;

    public CreateLogUseCase(ILogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void apply(String message) {
        logRepository.create(message).subscribe();
    }
}
