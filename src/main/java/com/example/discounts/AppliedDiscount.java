package com.example.discounts;

import com.example.Item;

import java.util.Formatter;

/**
 * Represents a discount applied to an item in a list of matching items
 */
public class AppliedDiscount {
    private Item item;
    private DiscountOffer offer;
    private double discountAmount;

    public AppliedDiscount(Item item, DiscountOffer offer, double discountAmount) {
        this.item = item;
        this.offer = offer;
        this.discountAmount = discountAmount;
    }

    public Item item() {
        return item;
    }

    public double discountAmount() {
        return discountAmount;
    }

    public DiscountOffer offer() {
        return offer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%-10s £ %5.2f (%s)",
                item.name(), -discountAmount(), offer().label());
        return sb.toString();
    }

    public enum Type {

        NoDiscount("Full Price"),
        TwoForThree("3 for 2"),
        TwoAtSpecialPrice("Buy 2 offer"),
        CheapestItemFree("Cheapest free"),
        FreeWithOtherItemsOffer("Free with others");

        Type(String label) {
            this.label = label;
        }

        private String label;

        public String label() {
            return label;
        }
    }
}
