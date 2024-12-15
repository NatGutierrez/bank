package com.example.bank.utils.operations;

import java.math.BigDecimal;

public final class CardPhysicalPurchaseOperation extends OperationType {
    public CardPhysicalPurchaseOperation(float cost, OperationAction action) {
        super(BigDecimal.valueOf(0), OperationAction.DEBIT);
    }

    @Override
    public String toString() {
        return "CardPhysicalPurchaseOperation{ " + super.toString() + " }";
    }
}
