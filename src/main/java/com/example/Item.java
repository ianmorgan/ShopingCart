package com.example;

import com.example.discounts.Offer;
import com.example.discounts.NoDiscount;

import java.util.Formatter;

/**
 * An item in the shopping cart
 */
public class Item {
    private final String name;
    private final double price;
    private final Offer discountOffer;

    public Item(String name, double price, Offer discountOffer) {
        this.name = name;
        this.price = price;
        this.discountOffer = discountOffer;
    }

    public Item(String name, double price) {
        this(name, price, new NoDiscount());
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    public Offer discountOffer() {
        return discountOffer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%-10s £ %5.2f", name, price);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Item) {
            Item otherItem = (Item) other;
            return this.price() == otherItem.price() &&
                    this.name().equals(otherItem.name()) &&
                    this.discountOffer().equals(otherItem.discountOffer());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name().hashCode();
    }

}
