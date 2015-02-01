package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public interface DiscountOffer {

    Discount apply(List<Item> items);

    Discount.Type type();
}
