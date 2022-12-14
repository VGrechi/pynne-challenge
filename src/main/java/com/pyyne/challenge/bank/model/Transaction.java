package com.pyyne.challenge.bank.model;

import com.pyyne.challenge.bank.enums.TransactionTypeEnum;

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
}
