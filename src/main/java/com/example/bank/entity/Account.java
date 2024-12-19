package com.example.bank.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;

@Document(collection = "accounts")
public class Account {
    @Id
    private int id;

    private String holder;

    private BigDecimal balance;

    private List<Operation> operations;

    public Account() {}

    public Account(int id) {
        this.id = id;
    }

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
