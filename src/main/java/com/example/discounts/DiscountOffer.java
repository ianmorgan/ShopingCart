package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * An interface that describes an offer. An implementation is required for
 * each type of discount. This works with a matching
 * {@link com.example.discounts.Discounter}, who's job is to collect matching items
 * until a discount can be triggered.
 *
 */
public interface DiscountOffer {

    /**
     * Calculate the discount when the offer is triggered
     * @param items
     * @return
     */
    Iterable<AppliedDiscount> apply(List<Item> items);

    /**
     * The type of discount this is linked to
     * @return
     */
    AppliedDiscount.Type type();

    /**
     * Label to print on the receipt
     * @return  the label string
     */
    String label();
}
