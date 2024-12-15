package com.example.bank.entity;

import com.example.bank.utils.operations.OperationAction;
import com.example.bank.utils.operations.OperationType;
import com.example.bank.utils.operations.OperationTypesEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OperationTypesEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    public Operation() {}

    public Operation(int id, BigDecimal value, OperationTypesEnum type, Account account) {
        this.id = id;
        this.value = value;
        this.type = type;
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

    public OperationTypesEnum getType() {
        return type;
    }

    public void setType(OperationTypesEnum type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static OperationType getOperationTypeObj(OperationTypesEnum type) {
        switch (type) {
            case ACCOUNT_DEPOSIT -> { return accountDepositOperation; }
            case ATM_DEPOSIT -> { return atmDepositOperation; }
            case ATM_EXTRACTION -> { return atmExtractionOperation; }
            case BRANCH_DEPOSIT -> { return branchDepositOperation; }
            case CARD_PHYSICAL_PURCHASE -> { return cardPhysicalPurchaseOperation; }
            case CARD_WEB_PURCHASE -> { return cardWebPurchaseOperation; }
        }

        throw new RuntimeException();
    }

    static OperationType accountDepositOperation =
            new OperationType(
                    OperationTypesEnum.ACCOUNT_DEPOSIT.name(),
                    BigDecimal.valueOf(1.5),
                    OperationAction.CREDIT
            );

    static OperationType atmDepositOperation =
            new OperationType(
                    OperationTypesEnum.ATM_DEPOSIT.name(),
                    BigDecimal.valueOf(2),
                    OperationAction.CREDIT
            );

    static OperationType atmExtractionOperation =
            new OperationType(
                    OperationTypesEnum.ATM_EXTRACTION.name(),
                    BigDecimal.valueOf(1),
                    OperationAction.DEBIT
            );

    static OperationType branchDepositOperation =
            new OperationType(
                    OperationTypesEnum.BRANCH_DEPOSIT.name(),
                    BigDecimal.valueOf(0),
                    OperationAction.CREDIT
            );

    static OperationType cardPhysicalPurchaseOperation =
            new OperationType(
                    OperationTypesEnum.CARD_PHYSICAL_PURCHASE.name(),
                    BigDecimal.valueOf(0),
                    OperationAction.DEBIT
            );

    static OperationType cardWebPurchaseOperation =
            new OperationType(
                    OperationTypesEnum.CARD_WEB_PURCHASE.name(),
                    BigDecimal.valueOf(5),
                    OperationAction.DEBIT
            );
}
