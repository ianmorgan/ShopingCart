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
    Map<DiscountOffer, List<Item>> matchingItems = new HashMap<DiscountOffer, List<Item>>();

    public Discount applyDiscount(Item item) {
        Discount result = null;
        if (Discount.Type.CheapestItemFree.equals(item.discountOffer().type())) {
            List<Item> items = matchingItems.get(item.discountOffer());
            if (items == null) {
                items = new ArrayList<Item>();
                matchingItems.put(item.discountOffer(), items);
            }
            items.add(item);

            if (items.size()==3) {
                result = new Discount(item, Discount.Type.CheapestItemFree, item.price());
                matchingItems.remove(item.discountOffer());
            }


        }

        return result;
    }

    public Discount.Type type() {
        return Discount.Type.CheapestItemFree;
    }

}
