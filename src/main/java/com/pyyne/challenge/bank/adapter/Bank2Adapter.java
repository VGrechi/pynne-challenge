package com.pyyne.challenge.bank.adapter;

import com.bank2.integration.Bank2AccountBalance;
import com.bank2.integration.Bank2AccountSource;
import com.bank2.integration.Bank2AccountTransaction;
import com.pyyne.challenge.bank.exceptions.AdapterInitializationException;
import com.pyyne.challenge.bank.enums.TransactionTypeEnum;
import com.pyyne.challenge.bank.exceptions.InvalidTransactionTypeException;
import com.pyyne.challenge.bank.model.Balance;
import com.pyyne.challenge.bank.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Bank2Adapter extends AbstractBankAdapter implements BankAdapter {

    private Bank2AccountSource source;

    public Bank2Adapter(Bank2AccountSource source) throws AdapterInitializationException {
        super(source);
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
        long accountId = 1L;
        List<Bank2AccountTransaction> sourceTransactions = source.getTransactions(1L, new Date(), new Date());
        return sourceTransactions.stream()
                .map(st -> new Transaction(2L, st.getAmount(), TransactionTypeEnum.getByLabel(String.valueOf(st.getType())), st.getText()))
                .collect(Collectors.toList());
    }

}
