package com.example.discounts.cheapestItemFree;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.DiscountOffer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class CheapestItemFreeOffer implements DiscountOffer {

    @Override
    public Iterable<AppliedDiscount> apply(List<Item> items) {
        double discount = Double.MAX_VALUE;
        Item discountedItem = null;

        for (Item item : items) {
            if (item.price() < discount) {
                discountedItem = item;
                discount = item.price();
            }
        }

        return Arrays.asList(new AppliedDiscount(discountedItem, this, discount));
    }

    @Override
    public AppliedDiscount.Type type() {
        return AppliedDiscount.Type.CheapestItemFree;
    }


}
