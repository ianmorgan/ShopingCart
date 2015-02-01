package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class CheapestItemFreeOffer implements DiscountOffer {

    @Override
    public Discount apply(List<Item> items) {
        double discount = Double.MAX_VALUE;
        Item discountedItem = null;

        for (Item item : items) {
            if (item.price() < discount) {
                discountedItem = item;
                discount = item.price();
            }
        }

        return new Discount(discountedItem, Discount.Type.CheapestItemFree,discount);
    }

    @Override
    public Discount.Type type() {
        return Discount.Type.CheapestItemFree;
    }


}
