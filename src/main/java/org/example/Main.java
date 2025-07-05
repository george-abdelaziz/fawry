package org.example;

import org.example.Entities.*;
import org.example.interfaces.IExpirable;
import org.example.interfaces.IShippable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        Date notExpired = cal.getTime();

        Product cheese = new Product("Cheese", 100, 10);
        Product tv = new Product("TV", 300, 5);
        Product scratchCard = new Product("Scratch Card", 50, 20);
        Product biscuits = new Product("Biscuits", 150, 5);

        Map<Product, IExpirable> expirables = new HashMap<>();
        expirables.put(cheese, new Expirable(notExpired));
        expirables.put(biscuits, new Expirable(notExpired));

        Map<Product, IShippable> shippables = new HashMap<>();
        shippables.put(cheese, new Shippable(cheese, 0.2));
        shippables.put(tv, new Shippable(tv, 1.5));
        shippables.put(biscuits, new Shippable(biscuits, 0.7));

        Customer customer = new Customer("George", 1000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        ECommerce.checkout(customer, cart, expirables, shippables);
    }
}
