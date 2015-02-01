package com.example.discounts;

import com.example.Item;

/**
 * Created by ianmorgan on 1/02/15.
 */
public interface Discounter {
    Discount applyDiscount(Item item);
}
