package com.example.bank.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "holder", nullable = false)
    private String holder;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "operations")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", targetEntity = Operation.class)
    @JsonManagedReference
    private List<Operation> operations;

    public Account() {}

    public Account(int id, String holder, BigDecimal balance, List<Operation> operations) {
        this.id = id;
        this.holder = holder;
        this.balance = balance;
        this.operations = new ArrayList<>(operations);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
