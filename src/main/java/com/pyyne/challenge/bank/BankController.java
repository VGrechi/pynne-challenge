package com.pyyne.challenge.bank;

import com.bank1.integration.Bank1AccountSource;
import com.bank2.integration.Bank2AccountSource;
import com.pyyne.challenge.bank.adapter.Bank1Adapter;
import com.pyyne.challenge.bank.adapter.Bank2Adapter;
import com.pyyne.challenge.bank.adapter.BankAdapter;
import com.pyyne.challenge.bank.service.BankService;

import java.util.Arrays;
import java.util.List;

/**
 * Controller that pulls information form multiple bank integrations and prints them to the console.
 *
 * Created by Par Renyard on 5/12/21.
 */
public class BankController {

    public void printBalances() {
        List<BankAdapter> bankList = Arrays.asList(
                new Bank1Adapter(new Bank1AccountSource()),
                new Bank2Adapter(new Bank2AccountSource()));
        BankService service = new BankService(bankList);
        service.getBalancesToPrint().forEach(b -> System.out.println(b));
        //System.out.println("Implement me to pull balance information from all available bank integrations and display them, one after the other.");
    }

    public void printTransactions() {
        System.out.println("Implement me to pull transactions from all available bank integrations and display them, one after the other.");
    }
}
