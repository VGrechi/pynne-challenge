package com.pyyne.challenge.bank.service;

import com.pyyne.challenge.bank.adapter.BankAdapter;
import com.pyyne.challenge.bank.model.Balance;
import com.pyyne.challenge.bank.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BankService {

    private List<BankAdapter> adapterList;

    public BankService(List<BankAdapter> adapterList) {
        this.adapterList = adapterList;
    }

    public List<String> getBalancesToPrint(){
        List<String> output = new ArrayList<>();

        this.adapterList.forEach(bankAdapter -> {
            Balance balance = bankAdapter.getBalance();
            output.add(balance.toString());
        });

        return output;
    }

    public List<String> getTransactionsToPrint(){
        List<String> output = new ArrayList<>();

        this.adapterList.forEach(bankAdapter -> {
            List<Transaction> transactions = bankAdapter.getTransactions();
            transactions.forEach(t -> output.add(t.toString()));
        });

        return output;
    }
}
