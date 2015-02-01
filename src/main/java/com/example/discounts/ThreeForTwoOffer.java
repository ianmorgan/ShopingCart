package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class ThreeForTwoOffer implements Offer {
    @Override
    public Discount apply(List<Item> items) {
        Item discountedItem = items.get(2);
        return new Discount(discountedItem, discountedItem.discountOffer(), items.get(2).price());
    }

    @Override
    public Discount.Type type() {
        return Discount.Type.TwoForThree;
    }


}
