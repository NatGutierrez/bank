package com.bank.config;

import com.bank.data.OperationEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IOperationMongoRepository extends ReactiveMongoRepository<OperationEntity, String> {}