package org.example.Entities;

public class Product {
    private static int idCounter = 1; // auto-incrementing ID counter
    private final int id;
    private final String name;
    private final double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
//        System.out.println("Created Product [ID=" + id + ", Name=" + name + ", Price=" + price + ", Quantity=" + quantity + "]");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > quantity) throw new RuntimeException("Out of stock");
        quantity -= amount;
//        System.out.println("Reduced quantity of " + name + ". Remaining: " + quantity);
    }
}
