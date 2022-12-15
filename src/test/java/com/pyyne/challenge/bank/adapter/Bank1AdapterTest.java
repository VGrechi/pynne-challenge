package com.pyyne.challenge.bank.adapter;

import com.bank1.integration.Bank1AccountSource;
import com.bank1.integration.Bank1Transaction;
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
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Bank1AdapterTest {

    @Test
    @DisplayName("Should return balance")
    public void shouldReturnBalance() throws AdapterInitializationException {
        //Given
        Bank1AccountSource bankSource = new Bank1AccountSource();
        Bank1Adapter adapter = new Bank1Adapter(bankSource);

        //When
        Balance result = adapter.getBalance();

        //Then
        assertNotNull(result);
        assertEquals(1L, result.getBankId());
        assertEquals(215.5d, result.getBalance());
        assertEquals("USD", result.getCurrency());
    }

    @Test
    @DisplayName("Should return transaction")
    public void shouldReturnTransactions() throws AdapterInitializationException {
        //Given
        Bank1AccountSource bankSource = new Bank1AccountSource();
        Bank1Adapter adapter = new Bank1Adapter(bankSource);

        //When
        List<Transaction> result = adapter.getTransactions();

        //Then
        List<Transaction>expected = Arrays.asList(
                new Transaction(1L, 100d, TransactionTypeEnum.CREDIT, "Check deposit"),
                new Transaction(1L, 25.5d, TransactionTypeEnum.DEBIT, "Debit card purchase"),
                new Transaction(1L, 225d, TransactionTypeEnum.DEBIT, "Rent payment")
        );
        assertEquals(3, result.size());
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    @DisplayName("Should not return transaction for invalid transaction type")
    public void shouldNotReturnTransactionForInvalidTransactionType() throws AdapterInitializationException {
        //Given
        Bank1AccountSource bankSource = mock(Bank1AccountSource.class);
        when(bankSource.getTransactions(anyLong(), any(Date.class), any(Date.class)))
                .thenReturn(Arrays.asList(new Bank1Transaction(100d, 99, "Check deposit")));
        Bank1Adapter adapter = new Bank1Adapter(bankSource);

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
            new Bank1Adapter(null);
        });
    }


}
