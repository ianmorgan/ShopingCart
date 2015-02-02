package com.example.discounts.freeWithOtherItems;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.DiscountOffer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by ianmorgan on 1/02/15.
 */
public class FreeWithOtherItemsOffer implements DiscountOffer {

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
    public Iterable<AppliedDiscount> apply(List<Item> items) {
        // this logic is within the Discounter, which suggests
        // this separation is not such a good idea
        throw new NotImplementedException();
    }

    @Override
    public AppliedDiscount.Type type() {
        return AppliedDiscount.Type.FreeWithOtherItemsOffer;
    }

    @Override
    public String label() {
        return type().label();
    }
}
