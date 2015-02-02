package com.example.discounts.freeWithOtherItems;

import com.example.Item;
import com.example.discounts.AppliedDiscount;
import com.example.discounts.Discounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Keep track of items in "Free with other Items" type offer
 * <p/>
 * TODO - current design only allows for one active offer combination
 */
public class FreeWithOtherItemsDiscounter implements Discounter {

    // it is possible for multiple instances of this offer to be running.
    private int primaryCount;
    private int unclaimedFreeCount;
    private List<Item> possibleFreeItems = new ArrayList<Item>();

    public Iterable<AppliedDiscount> checkDiscounts(Item item) {
        List<AppliedDiscount> result = new ArrayList<AppliedDiscount>();
        if (item.discountOffer() instanceof FreeWithOtherItemsOffer) {
            FreeWithOtherItemsOffer offer = (FreeWithOtherItemsOffer) item.discountOffer();

            // record counts
            if (offer.isPrimaryItem(item)) {
                primaryCount++;
                if (primaryCount == offer.numberToBuy()) {
                    unclaimedFreeCount += offer.numberFree();
                    primaryCount = 0;
                }
            } else {
                possibleFreeItems.add(item);
            }


            // add discounts for any eligible free items
            int numberToGiveAway = Math.min(unclaimedFreeCount, possibleFreeItems.size());
            for (int i = 0; i < numberToGiveAway; i++) {
                Item freeItem = possibleFreeItems.get(i);
                result.add(new AppliedDiscount(freeItem, offer, freeItem.price()));
            }

            // adjust internal buffers to remove those that have just been discounted
            for (int i = 0;i < numberToGiveAway; i++){
                possibleFreeItems.remove(0);
            }
            unclaimedFreeCount -= numberToGiveAway;
        }
        return result;
    }

}
