package com.pyyne.challenge.bank.enums;

import com.pyyne.challenge.bank.exceptions.InvalidTransactionTypeException;

import java.util.Arrays;
import java.util.Optional;

public enum TransactionTypeEnum {
    CREDIT(1),
    DEBIT(2);

    private int code;

    TransactionTypeEnum(int code) {
        this.code = code;
    }

    public static TransactionTypeEnum getByCode(int code) throws InvalidTransactionTypeException {
        Optional<TransactionTypeEnum> first = Arrays.stream(TransactionTypeEnum.values())
                .filter(tte -> tte.code == code)
                .findFirst();

        if(first.isPresent()) return first.get();
        else throw new InvalidTransactionTypeException();
    }

    public static TransactionTypeEnum getByLabel(String label) throws InvalidTransactionTypeException {
        try{
            return TransactionTypeEnum.valueOf(label);
        }catch (IllegalArgumentException ex){
            throw new InvalidTransactionTypeException();
        }
    }

}


