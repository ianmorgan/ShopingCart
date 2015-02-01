package com.example.discounts;

import com.example.Item;

import java.util.*;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class TwoAtSpecialPriceDiscounter implements Discounter {

    Set<Offer> matchedOffer = new HashSet<Offer>();

    @Override
    public Discount applyDiscount(Item item) {
        Discount result = null;
        if (Discount.Type.TwoAtSpecialPrice.equals(item.discountOffer().type())) {
            if (matchedOffer.contains(item.discountOffer()))   {
                matchedOffer.remove(item.discountOffer());

                //TODO - thsi is a cheat
                result =  item.discountOffer().apply(Arrays.asList(item,item));


            }
            else {
                matchedOffer.add(item.discountOffer())   ;
            }

        }

        return result;
    }
}
