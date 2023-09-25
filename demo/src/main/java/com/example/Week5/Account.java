package com.example.Week5;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Account {
    private double balance;
    private ArrayList<Transaction> transitionList = new ArrayList<Transaction>();

    /**
     * Make a deposit.
     */
    private void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("So tien ban nap vao khong hop le!");
            return;
        } else {
            this.balance += amount;
            transitionList.add(new Transaction("deposit", amount, this.balance));
        }
    }

    /**
     * Make a withdraw.
     */
    private void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("So tien ban rut ra khong hop le!");
        } else if (amount > balance) {
            System.out.println("So tien ban rut vuot qua so du!");
        } else {
            this.balance -= amount;
            transitionList.add(new Transaction("withdraw", amount, this.balance));
        }
    }

    /**
     * Make a new transaction.
     */
    public void addTransaction(double amount, String operation) {
        if (!operation.equals(Transaction.DEPOSIT) && !operation.equals(Transaction.WITHDRAW)) {
            System.out.println("Yeu cau khong hop le!");
            return;
        }
        if (operation.equals(Transaction.DEPOSIT)) {
            deposit(amount);
        } else if (operation.equals(Transaction.WITHDRAW)) {
            withdraw(amount);
        }
    }

    /**
     * Print all transitionList.
     */
    public void printTransaction() {
        for (int i = 0; i < transitionList.size(); i++) {
            DecimalFormat decimalFormat1 = new DecimalFormat("0.00");
            String amount = decimalFormat1.format(transitionList.get(i).getAmount());
            String balance = decimalFormat1.format(transitionList.get(i).getBalance());

            String operation;
            if (transitionList.get(i).getOperation().equals(Transaction.DEPOSIT)) {
                operation = "Nap tien";
            } else {
                operation = "Rut tien";
            }

            System.out.println("Giao dich " + (i + 1) + ": " + operation + " $" + amount
                    + ". So du luc nay: $" + balance + ".");
        }
    }
}
