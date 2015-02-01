package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class ThreeForTwoOffer implements DiscountOffer{
    @Override
    public Discount apply(List<Item> items) {
        return new Discount(items.get(2), Discount.Type.TwoForThree,items.get(2).price());
    }

    @Override
    public Discount.Type type() {
        return Discount.Type.TwoForThree;
    }


}
