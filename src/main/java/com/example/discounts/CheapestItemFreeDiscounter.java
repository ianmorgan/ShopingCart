package com.example.discounts;

import com.example.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class CheapestItemFreeDiscounter implements Discounter {
    Map<Offer, List<Item>> matchingItems = new HashMap<Offer, List<Item>>();

    public Discount applyDiscount(Item item) {
        Discount result = null;
        if (Discount.Type.CheapestItemFree.equals(item.discountOffer().type())) {
            Offer offer = item.discountOffer();
            List<Item> items = matchingItems.get(offer);
            if (items == null) {
                items = new ArrayList<Item>();
                matchingItems.put(offer, items);
            }
            items.add(item);

            if (items.size() == 3) {
                result = offer.apply(items);
                matchingItems.remove(offer);
            }
        }

        return result;
    }

    public Discount.Type type() {
        return Discount.Type.CheapestItemFree;
    }

}
