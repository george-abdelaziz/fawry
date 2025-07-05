package org.example.Entities;

import org.example.interfaces.IExpirable;

import java.util.Date;

public class Expirable implements IExpirable {
    private final Date expiryDate;

    public Expirable(Date expiryDate) {
        this.expiryDate = expiryDate;
//        System.out.println("Created ExpiryFeature with expiry date: " + expiryDate);
    }

    public boolean isExpired() {
        boolean expired = new Date().after(expiryDate);
//        System.out.println("Checking expiration. Expired: " + expired);
        return expired;
    }
}
