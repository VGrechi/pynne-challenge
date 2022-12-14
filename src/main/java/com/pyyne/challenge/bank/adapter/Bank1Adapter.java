package com.pyyne.challenge.bank.adapter;

import com.bank1.integration.Bank1AccountSource;
import com.pyyne.challenge.bank.model.Balance;
import com.pyyne.challenge.bank.model.Transaction;

import java.util.List;

public class Bank1Adapter implements BankAdapter {

    private Bank1AccountSource source;

    public Bank1Adapter(Bank1AccountSource source) {
        this.source = source;
    }

    @Override
    public Balance getBalance() {
        long accountId = 1L;
        Double sourceBalance = source.getAccountBalance(accountId);
        String sourceCurrency = source.getAccountCurrency(accountId);
        return new Balance(1L, sourceBalance, sourceCurrency);
    }

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

}
