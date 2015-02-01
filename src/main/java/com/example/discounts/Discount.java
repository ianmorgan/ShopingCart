package com.example.discounts;

import com.example.Item;

import java.util.Formatter;

/**
 * Represents a discount applied to an item in a list of matching items
 */
public class Discount {
    private Item item;
    private Offer offer;
    private double discountAmount;

    public Discount(Item item, Offer offer, double discountAmount) {
        this.item = item;
        this.offer = offer;
        this.discountAmount = discountAmount;
    }

    public Item item() {
        return item;
    }

    public Type discountRule() {
        return offer.type();
    }

    public double discountAmount() {
        return discountAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%-10s £ %5.2f (%s)",
                item.name(), -discountAmount(), discountRule().label());
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
