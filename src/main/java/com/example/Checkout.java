package com.example;

import com.example.discounts.Discount;
import com.example.discounts.Discounter;
import com.example.discounts.TwoForDisounter;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Represents a supermarket checkout. In this simple the
 * physical checkout and the actions of the shopper are represented
 * in a single class
 */
public class Checkout {

    private List<Discounter> disounters;

    public Checkout() {
        disounters = new ArrayList<Discounter>();
        disounters.add(new TwoForDisounter());
    }


    public String generateReceipt(Cart cart) {
        StringBuilder sb = new StringBuilder();
        List<Discount> discounts = new ArrayList<Discount>();

        double total = 0;
        for (Item item : cart.items()) {

            for (Discounter discounter : disounters) {
                Discount result = discounter.applyDiscount(item);

                if (result != null) {
                    discounts.add(result);
                    break;  //assume the businesss rule is only one discount possible
                }
            }

            total = total + item.price();
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(item.toString());
        }
        sb.append("\n");
        Formatter formatter = new Formatter(sb);

        if (discounts.size() > 0) {
            sb.append("DISCOUNTS\n");
            for (Discount discount : discounts) {
                total = total - discount.discountAmount();
                sb.append(discount.toString()).append("\n");
            }
        }
        formatter.format("TOTAL      £ %5.2f", total);
        return sb.toString();
    }
}
