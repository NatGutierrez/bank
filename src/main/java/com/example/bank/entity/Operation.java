package com.example.bank.entity;

import com.example.bank.utils.operations.OperationType;
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

    @JoinColumn(name = "type_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private OperationType type;

    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Account account;

    public Operation() {}

    public Operation(int id, BigDecimal value, OperationType type, Account account) {
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

    public OperationType getType() {
        return type;
    }

    public void setOperationType(OperationType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
