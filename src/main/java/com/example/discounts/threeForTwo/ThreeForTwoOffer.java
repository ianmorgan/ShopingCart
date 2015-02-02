package com.example.discounts.threeForTwo;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.DiscountOffer;

import java.util.Arrays;
import java.util.List;

/**
 * Apply the discount for a ThreeForTywo
 */
public class ThreeForTwoOffer implements DiscountOffer {
    @Override
    public Iterable<AppliedDiscount> apply(List<Item> items) {
        Item discountedItem = items.get(2);
        return Arrays.asList(new AppliedDiscount(discountedItem, discountedItem.discountOffer(), discountedItem.price()));
    }

    @Override
    public AppliedDiscount.Type type() {
        return AppliedDiscount.Type.TwoForThree;
    }

    @Override
    public String label() {
        return type().label();
    }
}
