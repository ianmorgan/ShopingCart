package com.example.discounts.cheapestItemFree;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.Discounter;
import com.example.discounts.Offer;

import java.util.*;

/**
 * Keep track of the items for a "Cheapest item is free" discount model
 */
public class CheapestItemFreeDiscounter implements Discounter {
    Map<Offer, List<Item>> matchingItems = new HashMap<Offer, List<Item>>();

    public Iterable<AppliedDiscount> checkDiscounts(Item item) {
        if (AppliedDiscount.Type.CheapestItemFree.equals(item.discountOffer().type())) {
            Offer offer = item.discountOffer();
            List<Item> items = matchingItems.get(offer);
            if (items == null) {
                items = new ArrayList<Item>();
                matchingItems.put(offer, items);
            }
            items.add(item);

            if (items.size() == 3) {
                matchingItems.remove(offer);
                return offer.apply(items);
            }
        }

        return Collections.EMPTY_LIST;
    }


}
