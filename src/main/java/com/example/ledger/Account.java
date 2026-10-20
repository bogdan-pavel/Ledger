
package com.example.ledger;

public class Account {

    private final String id;
    private long balance;

    public Account(String id, long initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        if (amount <= 0 ) {
            throw new IllegalArgumentException("Amount to deposit must be strictly positive");
        }

        balance += amount;
    }

    public void withdraw(long amount) {
        if (balance < 0 || balance < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }
}
