package com.example.discounts;

import com.example.Item;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class FreeWithOtherItemsOffer implements Offer {

    private int numberToBuy;
    private int numberFree;
    private Item primary;


    public FreeWithOtherItemsOffer(int numberToBuy, int numberFree) {
        this.numberToBuy = numberToBuy;
        this.numberFree = numberFree;
    }

    public void markPrimaryItem(Item item) {
        this.primary = item;
    }

    public boolean isPrimaryItem(Item item) {
        return item.equals(primary);
    }

    public int numberToBuy() {
        return numberToBuy;
    }

    public int numberFree() {
        return numberFree;
    }

    @Override
    public Discount apply(List<Item> items) {
        Item discountedItem = items.get(1);
        //return new Discount(discountedItem, discountedItem.discountOffer(), discount);

        return null;
    }

    @Override
    public Discount.Type type() {
        return Discount.Type.FreeWithOtherItemsOffer;
    }


}
