package com.example.discounts.twoAtSpecialPrice;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.Discounter;
import com.example.discounts.Offer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Keep track of items within a two at special price offer
 */
public class TwoAtSpecialPriceDiscounter implements Discounter {

    private Set<Offer> matchedOffer = new HashSet<Offer>();

    @Override
    public Iterable<AppliedDiscount> checkDiscounts(Item item) {
        AppliedDiscount result = null;
        if (AppliedDiscount.Type.TwoAtSpecialPrice.equals(item.discountOffer().type())) {
            if (matchedOffer.contains(item.discountOffer())) {
                matchedOffer.remove(item.discountOffer());

                //TODO - this is a cheat
                return item.discountOffer().apply(Arrays.asList(item, item));


            } else {
                matchedOffer.add(item.discountOffer());
            }

        }
        return Collections.EMPTY_LIST;
    }
}
