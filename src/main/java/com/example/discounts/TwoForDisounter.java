package com.example.discounts;

import com.example.Item;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class TwoForDisounter implements Discounter {
    Map<Item, Integer> matchingItems = new HashMap<Item, Integer>();

    public Discount applyDiscount(Item item) {
        Discount result = null;
        if (Item.DiscountRule.TwoForThree.equals(item.discountRule())) {
            Integer count = matchingItems.get(item);
            if (count == null) {
                matchingItems.put(item, 1);
            } else if (count == 2) {
                matchingItems.remove(item);
                result = new Discount(item, Item.DiscountRule.TwoForThree, item.price());

            } else {
                matchingItems.put(item, ++count);
            }
        }

        return result;
    }


}
