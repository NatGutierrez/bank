package com.example.bank.dto;

public class OperationDTO {
    private int id;

    private float value;

    private OperationTypeDTO operationType;

    private AccountDTO account;

    public OperationDTO() {}

    public OperationDTO(int id, float value, OperationTypeDTO operationType, AccountDTO account) {
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public OperationTypeDTO getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationTypeDTO operationType) {
        this.operationType = operationType;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
