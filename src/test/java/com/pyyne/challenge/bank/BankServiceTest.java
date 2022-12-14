package com.pyyne.challenge.bank;

import com.bank1.integration.Bank1AccountSource;
import com.bank2.integration.Bank2AccountSource;
import com.pyyne.challenge.bank.adapter.Bank1Adapter;
import com.pyyne.challenge.bank.adapter.Bank2Adapter;
import com.pyyne.challenge.bank.adapter.BankAdapter;
import com.pyyne.challenge.bank.service.BankService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

    @Test
    @DisplayName("Should return balance from two banks")
    public void shouldReturnBalanceFromTwoBanks(){
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
}
