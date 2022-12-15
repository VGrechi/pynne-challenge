package com.pyyne.challenge.bank.adapter;

import com.pyyne.challenge.bank.exceptions.AdapterInitializationException;

import java.util.Objects;

public abstract class AbstractBankAdapter {

    public AbstractBankAdapter(Object source) throws AdapterInitializationException {
        if(Objects.isNull(source)){
            throw new AdapterInitializationException();
        }
    }
}
