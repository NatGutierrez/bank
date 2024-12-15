package com.example.bank.utils.operations;

import java.math.BigDecimal;

public final class ATMExtractionOperation extends OperationType {
    public ATMExtractionOperation() {
        super(BigDecimal.valueOf(1), OperationAction.DEBIT);
    }

    @Override
    public String toString() {
        return "ATMExtractionOperation{ " + super.toString() + " }";
    }
}
