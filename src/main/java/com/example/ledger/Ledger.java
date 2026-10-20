
package com.example.ledger;

import java.util.HashMap;
import java.util.Map;

public class Ledger {

    private final Map<String, Account> accounts = new HashMap<>();

    public void createAccount(String id, long initialBalance) {
        if(accounts.containsKey(id)){
            throw new IllegalArgumentException("Account already exists");
        }
        accounts.put(id, new Account(id, initialBalance));
    }

    public Account getAccount(String id) {
        return accounts.get(id);
    }

    public void deposit(String accountId, long amount) {
        Account account = requireAccount(accountId);
        account.deposit(amount);
    }

    public void withdraw(String accountId, long amount) {
        Account account = requireAccount(accountId);
        account.withdraw(amount);
    }

    public void transfer(String fromId, String toId, long amount) {
        Account from = requireAccount(fromId);
        Account to = requireAccount(toId);

         Account first = fromId.compareTo(toId)<=0?from:to;
         Account second = first.equals(from)?to:from;

         synchronized (first){
             synchronized (second){
                 from.withdraw(amount);
                 to.deposit(amount);
             }
         }

    }

    private Account requireAccount(String id) {
        Account account = accounts.get(id);

        if (account == null) {
            throw new IllegalArgumentException("Unknown account: " + id);
        }

        return account;
    }
}