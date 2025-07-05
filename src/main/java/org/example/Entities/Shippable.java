package org.example.Entities;

import org.example.interfaces.IShippable;

public class Shippable implements IShippable {
    private final Product product;
    private final double weight;

    public Shippable(Product product, double weight) {
        this.product = product;
        this.weight = weight;
//        System.out.println("Created ShippingFeature for " + product.getName() + " with weight: " + weight + "kg");
    }

    public String getName() {
        return product.getName();
    }

    public double getWeight() {
        return weight;
    }
}
