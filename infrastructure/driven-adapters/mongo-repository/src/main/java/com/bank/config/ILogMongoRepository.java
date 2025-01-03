package com.bank.config;

import com.bank.data.LogEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ILogMongoRepository extends ReactiveMongoRepository<LogEntity, String> {
}
