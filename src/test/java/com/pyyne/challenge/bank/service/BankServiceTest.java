package com.pyyne.challenge.bank.service;

import com.bank1.integration.Bank1AccountSource;
import com.bank2.integration.Bank2AccountSource;
import com.pyyne.challenge.bank.exceptions.AdapterInitializationException;
import com.pyyne.challenge.bank.adapter.Bank1Adapter;
import com.pyyne.challenge.bank.adapter.Bank2Adapter;
import com.pyyne.challenge.bank.adapter.BankAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankServiceTest {

    @Test
    @DisplayName("Should return balances from two banks")
    public void shouldReturnBalancesFromTwoBanks() throws AdapterInitializationException {
        //Given
        List<BankAdapter> bankList = Arrays.asList(
                new Bank1Adapter(new Bank1AccountSource()),
                new Bank2Adapter(new Bank2AccountSource()));
        BankService service = new BankService(bankList);

        //When
        List<String> result = service.getBalancesToPrint();

        //Then
        List<String> expected = Arrays.asList(
                "Balance{bankId=1, balance=215.5, currency='USD'}",
                "Balance{bankId=2, balance=512.5, currency='USD'}");

        assertEquals(expected.size(), result.size());
        expected.stream().forEach(e -> {
            assertTrue(result.contains(e));
        });
    }

    @Test
    @DisplayName("Should not return balance if no bank was informed")
    public void shouldNotReturnBalance(){
        //Given
        List<BankAdapter> bankList = new ArrayList<>();
        BankService service = new BankService(bankList);

        //When
        List<String> result = service.getBalancesToPrint();

        //Then
        List<String> expected = new ArrayList<>();

        assertEquals(expected.size(), result.size());
    }

    @Test
    @DisplayName("Should return transactions from two banks")
    public void shouldReturnTransactionsFromTwoBanks() throws AdapterInitializationException {
        //Given
        List<BankAdapter> bankList = Arrays.asList(
                new Bank1Adapter(new Bank1AccountSource()),
                new Bank2Adapter(new Bank2AccountSource()));
        BankService service = new BankService(bankList);

        //When
        List<String> result = service.getTransactionsToPrint();

        //Then
        List<String> expected = Arrays.asList(
                "Transaction{bankId=1, amount=100.0, type=CREDIT, description='Check deposit'}",
                "Transaction{bankId=1, amount=25.5, type=DEBIT, description='Debit card purchase'}",
                "Transaction{bankId=1, amount=225.0, type=DEBIT, description='Rent payment'}",
                "Transaction{bankId=2, amount=125.0, type=DEBIT, description='Amazon.com'}",
                "Transaction{bankId=2, amount=500.0, type=DEBIT, description='Car insurance'}",
                "Transaction{bankId=2, amount=800.0, type=CREDIT, description='Salary'}");

        assertEquals(expected.size(), result.size());
        expected.stream().forEach(e -> {
            assertTrue(result.contains(e));
        });
    }

    @Test
    @DisplayName("Should not return transactions if no bank was informed")
    public void shouldNotReturnTransactions(){
        //Given
        List<BankAdapter> bankList = new ArrayList<>();
        BankService service = new BankService(bankList);

        //When
        List<String> result = service.getTransactionsToPrint();

        //Then
        List<String> expected = new ArrayList<>();

        assertEquals(expected.size(), result.size());
    }
}
