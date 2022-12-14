package com.pyyne.challenge.bank.model;

public class Balance {

    private Long bankId;
    private Double balance;
    private String currency;

    public Balance(Long bankId, Double balance, String currency) {
        this.bankId = bankId;
        this.balance = balance;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "bankId=" + bankId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
