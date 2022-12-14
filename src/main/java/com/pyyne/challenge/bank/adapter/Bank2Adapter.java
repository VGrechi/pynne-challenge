package com.pyyne.challenge.bank.adapter;

import com.bank1.integration.Bank1AccountSource;
import com.bank2.integration.Bank2AccountBalance;
import com.bank2.integration.Bank2AccountSource;
import com.pyyne.challenge.bank.model.Balance;
import com.pyyne.challenge.bank.model.Transaction;

import java.util.List;

public class Bank2Adapter implements BankAdapter {

    private Bank2AccountSource source;

    public Bank2Adapter(Bank2AccountSource source) {
        this.source = source;
    }

    @Override
    public Balance getBalance() {
        long accountId = 1L;
        Bank2AccountBalance sourceBalance = source.getBalance(accountId);
        return new Balance(2L, sourceBalance.getBalance(), sourceBalance.getCurrency());
    }

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

}
