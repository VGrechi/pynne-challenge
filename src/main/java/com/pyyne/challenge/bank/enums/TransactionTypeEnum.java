package com.pyyne.challenge.bank.enums;

public enum TransactionTypeEnum {
    CREDIT(1),
    DEBIT(2);

    private int code;

    TransactionTypeEnum(int code) {
        this.code = code;
    }

}


