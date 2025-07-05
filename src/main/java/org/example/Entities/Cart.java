package org.example.Entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static int idCounter = 1;
    private final int id;
    private List<CartItem> items = new ArrayList<>();

    public Cart() {
        this.id = idCounter++;
//        System.out.println("Created Cart with ID: " + id);
    }

    public int getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0 || quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Invalid quantity for " + product.getName());
        }
        items.add(new CartItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
