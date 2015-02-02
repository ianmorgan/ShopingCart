package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * An interface that describes an offer. An implementation is required for
 * each type of discount. This works with a matching {@link com.example.discounts.Discounter}
 * job is to collect matching items until a discount can be triggered.
 *
 */
public interface DiscountOffer {

    /**
     * Calculate the discount(s).
     * @param items
     * @return
     */
    Iterable<AppliedDiscount> apply(List<Item> items);

    AppliedDiscount.Type type();
}
