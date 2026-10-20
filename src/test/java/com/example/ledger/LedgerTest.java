
package com.example.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {

    @Test
    void createsAndRetrievesAccount() {
        Ledger ledger = new Ledger();

        ledger.createAccount("A1", 100);

        assertEquals(100, ledger.getAccount("A1").getBalance());
    }

    @Test
    void rejectsDuplicateAccountIds() {
        Ledger ledger = new Ledger();
        ledger.createAccount("A1", 100);

        assertThrows(
                IllegalArgumentException.class,
                () -> ledger.createAccount("A1", 200)
        );
    }

    @Test
    void transfersFundsBetweenAccounts() {
        Ledger ledger = new Ledger();
        ledger.createAccount("A1", 100);
        ledger.createAccount("A2", 50);

        ledger.transfer("A1", "A2", 30);

        assertEquals(70, ledger.getAccount("A1").getBalance());
        assertEquals(80, ledger.getAccount("A2").getBalance());
    }

    @Test
    void failedTransferDoesNotChangeEitherBalance() {
        Ledger ledger = new Ledger();
        ledger.createAccount("A1", 20);
        ledger.createAccount("A2", 50);

        assertThrows(
                IllegalStateException.class,
                () -> ledger.transfer("A1", "A2", 30)
        );

        assertEquals(20, ledger.getAccount("A1").getBalance());
        assertEquals(50, ledger.getAccount("A2").getBalance());
    }
}
