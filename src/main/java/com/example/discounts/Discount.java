package com.example.discounts;

import com.example.Item;

import java.util.Formatter;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class Discount {
    private Item item;
    private Item.DiscountRule discountRule;
    private double discountAmount;

    public Discount(Item item, Item.DiscountRule discountRule, double discountAmount) {
        this.item = item;
        this.discountRule = discountRule;
        this.discountAmount = discountAmount;
    }

    public Item item() {
        return item;
    }

    public Item.DiscountRule discountRule() {
        return discountRule;
    }

    public double discountAmount() {
        return discountAmount;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%-10s £ %5.2f %s", item.name(), discountAmount(), discountRule());
        return sb.toString();
    }

}
