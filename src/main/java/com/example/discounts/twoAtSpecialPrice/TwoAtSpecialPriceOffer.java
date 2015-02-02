package com.example.discounts.twoAtSpecialPrice;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.DiscountOffer;

import java.util.Arrays;
import java.util.List;

/**
 * Calculate the discounts to be applied on a 2 at a special price offer
 */
public class TwoAtSpecialPriceOffer implements DiscountOffer {
    private double discount;
    private String label;

    public TwoAtSpecialPriceOffer(String label, double discount) {
        this.label = label;
        this.discount = discount;
    }

    @Override
    public Iterable<AppliedDiscount> apply(List<Item> items) {
        Item discountedItem = items.get(1);
        return Arrays.asList(new AppliedDiscount(discountedItem, discountedItem.discountOffer(), discount));
    }

    @Override
    public AppliedDiscount.Type type() {
        return AppliedDiscount.Type.TwoAtSpecialPrice;
    }

    @Override
    public String label() {
        return this.label;
    }


}
