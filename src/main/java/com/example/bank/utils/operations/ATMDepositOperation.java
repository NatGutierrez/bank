package com.example.bank.utils.operations;

import java.math.BigDecimal;

public final class ATMDepositOperation extends OperationType {
    public ATMDepositOperation(float cost, OperationAction action) {
        super(BigDecimal.valueOf(2), OperationAction.CREDIT);
    }

    @Override
    public String toString() {
        return "ATMDepositOperation{ " + super.toString() + " }";
    }
}
