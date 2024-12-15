package com.example.bank.utils.operations;

import java.math.BigDecimal;

public class BranchDepositOperation extends OperationType {
    public BranchDepositOperation(float cost, OperationAction action) {
        super(BigDecimal.valueOf(0), OperationAction.CREDIT);
    }

    @Override
    public String toString() {
        return "BranchDepositOperation{ " + super.toString() + " }";
    }
}
