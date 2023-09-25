package com.example.Week5;

public class Transaction {
    private String operation;
    private double amount;
    private double balance;

    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";

    /**
     * Init the transaction.
     */
    public Transaction(String operation, double amount, double balance) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    /**
     * Get the operation.
     */
    public String getOperation() {
        return this.operation;
    }

    /**
     * Set the operation.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Get the amount.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Set the amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the balance.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Set the balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
