package com.example.bank.utils.operations;

import java.math.BigDecimal;

public abstract class OperationType {
    public final BigDecimal cost;

    public final OperationAction action;

    public OperationType(BigDecimal cost, OperationAction action) {
        this.cost = cost;
        this.action = action;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public OperationAction getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "cost=" + cost + ", action=" + action;
    }
}
