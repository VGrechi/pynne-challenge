package com.pyyne.challenge.bank.model;

import com.pyyne.challenge.bank.enums.TransactionTypeEnum;

import java.util.Objects;

public class Transaction {

    private Long bankId;
    private Double amount;
    private TransactionTypeEnum type;
    private String description;

    public Transaction(Long bankId, Double amount, TransactionTypeEnum type, String description) {
        this.bankId = bankId;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "bankId=" + bankId +
                ", amount=" + amount +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(bankId, that.bankId) && Objects.equals(amount, that.amount) && type == that.type && Objects.equals(description, that.description);
    }
}
