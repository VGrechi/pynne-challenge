package com.pyyne.challenge.bank.adapter;

import com.bank1.integration.Bank1AccountSource;
import com.bank1.integration.Bank1Transaction;
import com.bank2.integration.Bank2AccountSource;
import com.bank2.integration.Bank2AccountTransaction;
import com.pyyne.challenge.bank.enums.TransactionTypeEnum;
import com.pyyne.challenge.bank.exceptions.AdapterInitializationException;
import com.pyyne.challenge.bank.exceptions.InvalidTransactionTypeException;
import com.pyyne.challenge.bank.model.Balance;
import com.pyyne.challenge.bank.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Bank2AdapterTest {

    @Test
    @DisplayName("Should return balance")
    public void shouldReturnBalance() throws AdapterInitializationException {
        //Given
        Bank2AccountSource bankSource = new Bank2AccountSource();
        Bank2Adapter adapter = new Bank2Adapter(bankSource);

        //When
        Balance result = adapter.getBalance();

        //Then
        assertNotNull(result);
        assertEquals(2L, result.getBankId());
        assertEquals(512.5d, result.getBalance());
        assertEquals("USD", result.getCurrency());
    }

    @Test
    @DisplayName("Should return transaction")
    public void shouldReturnTransactions() throws AdapterInitializationException {
        //Given
        Bank2AccountSource bankSource = new Bank2AccountSource();
        Bank2Adapter adapter = new Bank2Adapter(bankSource);

        //When
        List<Transaction> result = adapter.getTransactions();

        //Then
        List<Transaction>expected = Arrays.asList(
                new Transaction(2L, 125d, TransactionTypeEnum.DEBIT, "Amazon.com"),
                new Transaction(2L, 500d, TransactionTypeEnum.DEBIT, "Car insurance"),
                new Transaction(2L, 800d, TransactionTypeEnum.CREDIT, "Salary")
        );
        assertEquals(3, result.size());
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    @DisplayName("Should not return transaction for invalid transaction type")
    public void shouldNotReturnTransactionForInvalidTransactionType() throws AdapterInitializationException {
        //Given
        Bank2AccountSource bankSource = mock(Bank2AccountSource.class);
        when(bankSource.getTransactions(anyLong(), any(Date.class), any(Date.class)))
                .thenReturn(Arrays.asList(new Bank2AccountTransaction(100d, null, "Amazon.com")));
        Bank2Adapter adapter = new Bank2Adapter(bankSource);

        //When
        //Then
        assertThrows(InvalidTransactionTypeException.class, () -> {
            adapter.getTransactions();
        });
    }

    @Test
    @DisplayName("Should not initialize adapter if no bank was informed")
    public void shouldNotInitializeAdapter() {
        //Given

        //When
        //Then
        assertThrows(AdapterInitializationException.class, () -> {
            new Bank2Adapter(null);
        });
    }


}
