package com.example;

import java.util.Formatter;

/**
 * An item in the shopping cart
 */
public class Item {
    private final String name;
    private final double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%-10s £ %5.2f", name, price);
        return sb.toString();
    }
}
