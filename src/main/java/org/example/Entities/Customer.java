package org.example.Entities;

public class Customer {
    private static int idCounter = 1;
    private final int id;
    private final String name;
    private double balance;

    public Customer(String name, double balance) {
        this.id = idCounter++;
        this.name = name;
        this.balance = balance;
//        System.out.println("Created Customer [ID=" + id + ", Name=" + name + ", Balance=" + balance + "]");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean charge(double amount) {
//        System.out.println("Attempting to charge " + amount + " from " + name);
        if (balance < amount) {
//            System.out.println("Insufficient balance. Available: " + balance);
            return false;
        }
        balance -= amount;
//        System.out.println("Charged successfully. New balance: " + balance);
        return true;
    }
}
