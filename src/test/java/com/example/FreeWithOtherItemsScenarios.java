package com.example;

import com.example.discounts.FreeWithOtherItemsOffer;
import org.testng.annotations.Test;

public class FreeWithOtherItemsScenarios extends BaseCheckoutScenarios {
    private FreeWithOtherItemsOffer oneFreeWithThree = new FreeWithOtherItemsOffer(3, 1);

    @Test
    public void freeItemsIfOtherItemsPurchased() {
        Item coffee = new Item("Coffee", 3.99, oneFreeWithThree);
        Item tea = new Item("Tea", 1.95, oneFreeWithThree);

        // OK - so this has hit the point where the domain model
        //      is too simple. There is really a join entity
        //      that relates offers to discounts - this would also
        //      make it easier to model situations where multiple
        //      discounts can be applied to any item.
        oneFreeWithThree.markPrimaryItem(coffee);

        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(tea);

        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Tea        £  1.95\n" +
                "DISCOUNTS\n" +
                "Tea        £ -1.95 (Free with others)\n" +
                "TOTAL      £ 11.97");
    }

    @Test
    public void freeItemsIfOtherItemsPurchasedTriggersOutOfOrder() {
        Item coffee = new Item("Coffee", 3.99, oneFreeWithThree);
        Item tea = new Item("Tea", 1.95, oneFreeWithThree);
        oneFreeWithThree.markPrimaryItem(coffee);

        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(tea);
        cart.addItem(coffee);

        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Tea        £  1.95\n" +
                "Coffee     £  3.99\n" +
                "DISCOUNTS\n" +
                "Tea        £ -1.95 (Free with others)\n" +
                "TOTAL      £ 11.97");
    }

    @Test
    public void itemsInExcessOfFreeLimitIgnored() {
        Item coffee = new Item("Coffee", 3.99, oneFreeWithThree);
        Item tea = new Item("Tea", 1.95, oneFreeWithThree);
        oneFreeWithThree.markPrimaryItem(coffee);

        cart.addItem(tea);
        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(tea);
        cart.addItem(coffee);

        assertReceipt("Tea        £  1.95\n" +
                "Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Tea        £  1.95\n" +
                "Coffee     £  3.99\n" +
                "DISCOUNTS\n" +
                "Tea        £ -1.95 (Free with others)\n" +
                "TOTAL      £ 13.92");
    }



}
