package com.example.discounts;

import com.example.Item;

/**
 * Accumulate items that match a discount rules.
 */
public interface Discounter {
    Discount applyDiscount(Item item);
}
