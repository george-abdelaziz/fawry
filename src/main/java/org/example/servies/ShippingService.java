package org.example.servies;

import org.example.interfaces.IShippable;

import java.util.List;
import java.util.Map;

public class ShippingService {
    public static void ship(List<IShippable> items, Map<IShippable, Integer> quantities) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (IShippable item : items) {
            int qty = quantities.get(item);
            double itemWeight = item.getWeight() * qty;
            System.out.println(qty + "x " + item.getName() + " " + itemWeight * 1000 + "g");
            totalWeight += itemWeight;
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
}
