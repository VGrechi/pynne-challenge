package com.pyyne.challenge.bank.adapter;

import com.pyyne.challenge.bank.exceptions.InvalidTransactionTypeException;
import com.pyyne.challenge.bank.model.Balance;
import com.pyyne.challenge.bank.model.Transaction;

import java.util.List;

public interface BankAdapter {

    Balance getBalance();

    List<Transaction> getTransactions();

}
