package com.example.discounts.threeForTwo;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.Discounter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Keep track of items within a ThreeForTwo offer
 */
public class ThreeForTwoDiscounter implements Discounter {
    Map<Item, Integer> matchingItems = new HashMap<Item, Integer>();

    public Iterable<AppliedDiscount> checkDiscounts(Item item) {
        AppliedDiscount result = null;
        if (AppliedDiscount.Type.TwoForThree.equals(item.discountOffer().type())) {
            Integer count = matchingItems.get(item);
            if (count == null) {
                matchingItems.put(item, 1);
            } else if (count == 2) {
                matchingItems.remove(item);
                return Arrays.asList(new AppliedDiscount(item, item.discountOffer(), item.price()));

            } else {
                matchingItems.put(item, ++count);
            }
        }

        return Collections.EMPTY_LIST;
    }


}
