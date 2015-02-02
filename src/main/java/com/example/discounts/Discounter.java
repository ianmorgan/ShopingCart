package com.example.discounts;

import com.example.Item;

/**
 * Accumulate items that match a discount rules.
 * Follows an accumulator type pattern. Different implementation
 * are required for each discount as the rules for accumulating
 * items that match the offer will vary.
 */
public interface Discounter {

    /**
     * Check the item. If it has an offer attached to it that matched the discount
     * rule then store this item and when enough items have been received to
     * match the rule then return the details of the discount to be applied to the
     * receipt.
     * <p/>
     * In this model the discount can be displayed on the receipt / terminal as soon as the
     * last item that matches the rule is scanned.
     *
     * @param item
     * @return the applied discount or null if adding this item
     * didn't trigger a new discount.
     */
    Iterable<AppliedDiscount> checkDiscounts(Item item);


}
