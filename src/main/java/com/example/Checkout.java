package com.example;

import com.example.discounts.AppliedDiscount;
import com.example.discounts.Discounter;
import com.example.discounts.cheapestItemFree.CheapestItemFreeDiscounter;
import com.example.discounts.freeWithOtherItems.FreeWithOtherItemsDiscounter;
import com.example.discounts.threeForTwo.ThreeForTwoDiscounter;
import com.example.discounts.twoAtSpecialPrice.TwoAtSpecialPriceDiscounter;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Represents a supermarket checkout. In this simple model the
 * physical checkout and the actions of the shopper are represented
 * in a single class
 */
public class Checkout {

    private List<Discounter> discounters;

    public Checkout() {
        discounters = new ArrayList<Discounter>();
        discounters.add(new ThreeForTwoDiscounter());
        discounters.add(new TwoAtSpecialPriceDiscounter());
        discounters.add(new CheapestItemFreeDiscounter());
        discounters.add(new FreeWithOtherItemsDiscounter());
    }


    public String generateReceipt(Cart cart) {
        StringBuilder sb = new StringBuilder();
        List<AppliedDiscount> discounts = new ArrayList<AppliedDiscount>();

        double total = 0;
        for (Item item : cart.items()) {

            for (Discounter discounter : discounters) {
                for (AppliedDiscount appliedDiscount : discounter.checkDiscounts(item)) {
                    discounts.add(appliedDiscount);
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
            for (AppliedDiscount discount : discounts) {
                total = total - discount.discountAmount();
                sb.append(discount.toString()).append("\n");
            }
        }
        formatter.format("TOTAL      £ %5.2f", total);
        return sb.toString();
    }
}
