package com.example.bank.entity;

import com.example.bank.utils.OperationAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "operation_type")
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private float cost;

    @Column(name = "action", nullable = false)
    private OperationAction action;

    @Column(name = "operations")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", targetEntity = Operation.class)
    @JsonIgnore
    private List<Operation> operations;

    public OperationType() {}

    public OperationType(int id, String name, float cost, OperationAction action) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public OperationAction getAction() {
        return action;
    }

    public void setAction(OperationAction action) {
        this.action = action;
    }
}
