package com.example.discounts;

import com.example.Item;

import java.util.Formatter;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class Discount {
    private Item item;
    private Type type;
    private double discountAmount;

    public Discount(Item item, Type type, double discountAmount) {
        this.item = item;
        this.type = type;
        this.discountAmount = discountAmount;
    }

    public Item item() {
        return item;
    }

    public Type discountRule() {
        return type;
    }

    public double discountAmount() {
        return discountAmount;
    }

    @Override
    public String toString(){
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
        CheapestItemFree ("Cheapest free");

        Type(String label) {
            this.label = label;
        }

        private String label;

        public String label() {
            return label;
        }
    }
}
