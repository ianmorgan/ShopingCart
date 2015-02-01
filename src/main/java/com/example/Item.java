package com.example;

import java.util.Formatter;

/**
 * An item in the shopping cart
 */
public class Item {
    private final String name;
    private final double price;
    private final DiscountRule discountRule;

    public Item(String name, double price, DiscountRule discountRule) {
        this.name = name;
        this.price = price;
        this.discountRule = discountRule;
    }

    public Item(String name, double price) {
        this(name, price, DiscountRule.NoDiscount);
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    public DiscountRule discountRule() {
        return discountRule;
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
                    this.discountRule().equals(otherItem.discountRule());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name().hashCode();
    }

    public enum DiscountRule {
        NoDiscount,
        TwoForThree
    }
}
