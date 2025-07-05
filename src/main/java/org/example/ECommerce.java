package org.example;

import org.example.Entities.Cart;
import org.example.Entities.CartItem;
import org.example.Entities.Customer;
import org.example.Entities.Product;
import org.example.interfaces.IExpirable;
import org.example.interfaces.IShippable;
import org.example.servies.ShippingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECommerce {
    private static final double SHIPPING_RATE_PER_KG = 30;

    public static void checkout(Customer customer, Cart cart,
                                Map<Product, IExpirable> expirables, Map<Product, IShippable> shippables) {

        System.out.println("Starting checkout for customer: " + customer.getName());

        if (cart.isEmpty()) throw new RuntimeException("Cart is empty");

        double subtotal = 0;
        double totalWeight = 0;
        List<IShippable> toShip = new ArrayList<>();
        Map<IShippable, Integer> shippingQuantities = new HashMap<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
//            System.out.println("Processing item: " + quantity + "x " + product.getName());

            IExpirable exp = expirables.get(product);
            if (exp != null && exp.isExpired()) {
                throw new RuntimeException("Product " + product.getName() + " is expired");
            }

            if (quantity > product.getQuantity()) {
                throw new RuntimeException("Product " + product.getName() + " is out of stock");
            }

            subtotal += product.getPrice() * quantity;
            product.reduceQuantity(quantity);

            IShippable ship = shippables.get(product);
            if (ship != null) {
                toShip.add(ship);
                shippingQuantities.put(ship, shippingQuantities.getOrDefault(ship, 0) + quantity);
                totalWeight += ship.getWeight() * quantity;
            }
        }

        double shipping = totalWeight * SHIPPING_RATE_PER_KG;
        double total = subtotal + shipping;

        System.out.printf("Subtotal: %.2f, Shipping: %.2f, Total: %.2f\n", subtotal, shipping, total);

        if (!customer.charge(total)) {
            throw new RuntimeException("Insufficient balance");
        }

        if (!toShip.isEmpty()) {
            ShippingService.ship(toShip, shippingQuantities);
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            System.out.println(quantity + "x " + product.getName() + " " + (product.getPrice() * quantity));
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shipping);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Balance %.0f\n", customer.getBalance());
//        System.out.println("END.");
    }
}
