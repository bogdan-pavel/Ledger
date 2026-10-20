
package com.example.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void createsAccountWithInitialBalance() {
        Account account = new Account("A1", 100);

        assertEquals("A1", account.getId());
        assertEquals(100, account.getBalance());
    }

    @Test
    void depositsMoney() {
        Account account = new Account("A1", 100);

        account.deposit(50);

        assertEquals(150, account.getBalance());
    }

    @Test
    void rejectsWithdrawalWhenBalanceIsInsufficient() {
        Account account = new Account("A1", 100);

        assertThrows(
                IllegalStateException.class,
                () -> account.withdraw(120)
        );

        assertEquals(100, account.getBalance());
    }

    @Test
    void rejectsNegativeDeposit() {
        Account account = new Account("A1", 100);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(-10)
        );

        assertEquals(100, account.getBalance());
    }
}
