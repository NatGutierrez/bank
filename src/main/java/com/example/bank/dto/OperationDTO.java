package com.example.bank.dto;

import com.example.bank.utils.operations.OperationTypesEnum;

import java.math.BigDecimal;

public class OperationDTO {
    private int id;

    private BigDecimal value;

    private OperationTypesEnum type;

    private int accountId;

    public OperationDTO() {}

    public OperationDTO(int id, BigDecimal value, OperationTypesEnum type, int accountId) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.accountId = accountId;
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

    public OperationTypesEnum getType() {
        return type;
    }

    public void setType(OperationTypesEnum type) {
        this.type = type;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
