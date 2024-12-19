package com.example.bank.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountRequestDTO {
    private String id;

    private String holder;

    private BigDecimal balance;

    private List<OperationRequestDTO> operations;

    public void init() {
        this.id = UUID.randomUUID().toString().substring(0,9);
        this.balance = BigDecimal.ZERO;
        this.operations = new ArrayList<>();
    }

    public AccountRequestDTO() {}

    // Create with default balance
    public AccountRequestDTO(String holder) {
        this.init();
        this.holder = holder;
    }

    // Create with custom balance
    public AccountRequestDTO(String holder, BigDecimal balance) {
        this.init();
        this.holder = holder;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<OperationRequestDTO> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationRequestDTO> operations) {
        this.operations = operations;
    }
}
