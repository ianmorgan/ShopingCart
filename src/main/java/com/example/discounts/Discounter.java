package com.example.discounts;

import com.example.Item;

/**
 * Accumulate items that match a discount rules.
 * Follows an accumulator type pattern.
 * <p/>
 * In the current an implementation is required for each new discount /
 * special offer rule, but they often followed a common pattern, which suggests
 * this could be refined to a generic class.
 */
public interface Discounter {

    /**
     * Check if the item matches
     *
     * @param item
     * @return
     */
    Discount applyDiscount(Item item);


}
