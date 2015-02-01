package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class TwoAtSpecialPriceOffer implements DiscountOffer {
    private double discount;

    public TwoAtSpecialPriceOffer(double discount) {
        this.discount = discount;
    }

    @Override
    public Discount apply(List<Item> items) {
        return new Discount(items.get(1),this.type(),discount);
    }

    @Override
    public Discount.Type type() {
        return Discount.Type.TwoAtSpecialPrice;
    }


}
