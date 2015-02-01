package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class TwoAtSpecialPriceOffer implements Offer {
    private double discount;

    public TwoAtSpecialPriceOffer(double discount) {
        this.discount = discount;
    }

    @Override
    public Discount apply(List<Item> items) {
        Item discountedItem = items.get(1);
        return new Discount(discountedItem, discountedItem.discountOffer(), discount);
    }

    @Override
    public Discount.Type type() {
        return Discount.Type.TwoAtSpecialPrice;
    }


}
