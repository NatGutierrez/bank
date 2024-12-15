package com.example.bank.utils.operations;

import java.math.BigDecimal;

public final class CardWebPurchaseOperation extends OperationType {
    public CardWebPurchaseOperation(float cost, OperationAction action) {
        super(BigDecimal.valueOf(5), OperationAction.DEBIT);
    }

    @Override
    public String toString() {
        return "CardWebPurchaseOperation{ " + super.toString() + " }";
    }
}
