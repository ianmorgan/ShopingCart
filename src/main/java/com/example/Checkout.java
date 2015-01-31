package com.example;

import java.util.Formatter;

/**
 * Represents a supermarket checkout. In this simple the
 * physical checkout and the actions of the shopper are represented
 * in a single class
 */
public class Checkout {

    public String generateReceipt(Cart cart) {
        StringBuilder sb = new StringBuilder();

        double total = 0;
        for (Item item : cart.items()) {
            total = total + item.price();
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(item.toString());
        }
        sb.append("\n");
        Formatter formatter = new Formatter(sb);
        formatter.format("TOTAL      £ %5.2f", total);
        return sb.toString();
    }
}
