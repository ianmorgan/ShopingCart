package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class NoDiscount implements Offer {
    @Override
    public Discount apply(List<Item> items) {
        return null;
    }

    @Override
    public Discount.Type type() {
        return Discount.Type.NoDiscount;
    }
}
