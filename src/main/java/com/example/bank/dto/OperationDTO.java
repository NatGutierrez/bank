package com.example.bank.dto;

import com.example.bank.utils.operations.OperationType;

import java.math.BigDecimal;

public class OperationDTO {
    private int id;

    private BigDecimal value;

    private OperationType operationType;

    private AccountDTO account;

    public OperationDTO() {}

    public OperationDTO(int id, BigDecimal value, OperationType operationType, AccountDTO account) {
        this.id = id;
        this.value = value;
        this.operationType = operationType;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
