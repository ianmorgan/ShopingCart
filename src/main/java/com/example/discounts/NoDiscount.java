package com.example.discounts;

import com.example.Item;

import java.util.Collections;
import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class NoDiscount implements DiscountOffer {
    @Override
    public Iterable<AppliedDiscount> apply(List<Item> items) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public AppliedDiscount.Type type() {
        return AppliedDiscount.Type.NoDiscount;
    }
}
