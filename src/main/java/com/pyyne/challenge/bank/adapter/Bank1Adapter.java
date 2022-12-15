package com.pyyne.challenge.bank.adapter;

import com.bank1.integration.Bank1AccountSource;
import com.bank1.integration.Bank1Transaction;
import com.pyyne.challenge.bank.exceptions.AdapterInitializationException;
import com.pyyne.challenge.bank.enums.TransactionTypeEnum;
import com.pyyne.challenge.bank.exceptions.InvalidTransactionTypeException;
import com.pyyne.challenge.bank.model.Balance;
import com.pyyne.challenge.bank.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Bank1Adapter extends AbstractBankAdapter implements BankAdapter {

    private Bank1AccountSource source;

    public Bank1Adapter(Bank1AccountSource source) throws AdapterInitializationException {
        super(source);
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
        List<Bank1Transaction> sourceTransactions = source.getTransactions(1L, new Date(), new Date());
        return sourceTransactions.stream()
                .map(st -> new Transaction(1L, st.getAmount(), TransactionTypeEnum.getByCode(st.getType()), st.getText()))
                .collect(Collectors.toList());
    }

}
