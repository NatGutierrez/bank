package com.example.bank.utils.operations;

import java.math.BigDecimal;

public final class AccountDepositOperation extends OperationType {
    public AccountDepositOperation(float cost, OperationAction action) {
        super(BigDecimal.valueOf(1.5), OperationAction.CREDIT);
    }

    @Override
    public String toString() {
        return "AccountDepositOperation{ " + super.toString() + " }";
    }
}
