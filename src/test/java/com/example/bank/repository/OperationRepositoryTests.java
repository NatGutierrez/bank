package com.example.bank.repository;

import com.example.bank.entity.Account;
import com.example.bank.entity.Operation;
import com.example.bank.utils.operations.OperationTypesEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class OperationRepositoryTests {
    @Autowired
    OperationRepository operationRepository;

    private Operation operation;
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("123", "holder", BigDecimal.ZERO, new ArrayList<>());
        operation = new Operation("123", BigDecimal.valueOf(1000), OperationTypesEnum.ACCOUNT_DEPOSIT, account);
    }

    @Test
    public void testSaveOperation() {
        Operation savedAccount = operationRepository.save(operation);
        assertEquals(account.getId(), savedAccount.getId());
    }

    @Test
    public void testFindAllOperations() {
        List<Operation> operations = operationRepository.findAll();
        assertNotNull(operations);
    }

    @Test
    public void testFindOperationById() {
        Operation savedOperation = operationRepository.save(operation);
        Optional<Operation> foundOperation = operationRepository.findById(operation.getId());
        assertNotNull(foundOperation);
        assertEquals(savedOperation.getId(), foundOperation.get().getId());
    }
}
