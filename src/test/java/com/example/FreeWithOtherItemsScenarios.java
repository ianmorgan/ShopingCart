package com.example;

import com.example.discounts.freeWithOtherItems.FreeWithOtherItemsOffer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.fail;

@Test
public class FreeWithOtherItemsScenarios extends BaseCheckoutScenarios {
    private FreeWithOtherItemsOffer oneFreeWithThree = new FreeWithOtherItemsOffer(3, 1);
    private FreeWithOtherItemsOffer twoFreeWithTwo = new FreeWithOtherItemsOffer(2, 2);

    @Test
    public void freeItemsIfOtherItemsPurchased() {
        Item coffee = new Item("Coffee", 3.99, oneFreeWithThree);
        Item tea = new Item("Tea", 1.95, oneFreeWithThree);

        // OK - I need a setter, so this has hit the point where the
        //      domain model is too simple.
        //
        //      There is really a join entity that relates offers to discounts - this would also
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
                "TOTAL      £ 11.96");
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

    @Test
    public void canHaveTheSameOfferMultipleTimes() {
        Item coffee = new Item("Coffee", 3.99, oneFreeWithThree);
        Item tea = new Item("Tea", 1.95, oneFreeWithThree);
        oneFreeWithThree.markPrimaryItem(coffee);

        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(tea);
        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(coffee);
        cart.addItem(tea);

        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Tea        £  1.95\n" +
                "Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Tea        £  1.95\n" +
                "DISCOUNTS\n" +
                "Tea        £ -1.95 (Free with others)\n" +
                "Tea        £ -1.95 (Free with others)\n" +
                "TOTAL      £ 23.94");
    }

    @Test
    public void canHaveMultipleFreeItemsInSingleOffer() {
        Item coke = new Item("Coke", 1.00, twoFreeWithTwo);
        Item crisps = new Item("Crisps", 0.25, twoFreeWithTwo);
        twoFreeWithTwo.markPrimaryItem(coke);

        cart.addItem(coke);
        cart.addItem(crisps);
        cart.addItem(crisps);
        cart.addItem(coke);

        assertReceipt("Coke       £  1.00\n" +
                "Crisps     £  0.25\n" +
                "Crisps     £  0.25\n" +
                "Coke       £  1.00\n" +
                "DISCOUNTS\n" +
                "Crisps     £ -0.25 (Free with others)\n" +
                "Crisps     £ -0.25 (Free with others)\n" +
                "TOTAL      £  2.00");
    }

    @Test
    public void canHaveFreeItemsRegardlessOfOrderInCart() {
        Item coke = new Item("Coke", 1.00, twoFreeWithTwo);
        Item crisps = new Item("Crisps", 0.25, twoFreeWithTwo);
        twoFreeWithTwo.markPrimaryItem(coke);


        // should have 4 cokes and 4 free crisps and 1 paid for crisps
        List<Item> items = new ArrayList<Item>();
        items.add(crisps);
        items.add(crisps);
        items.add(crisps);
        items.add(crisps);
        items.add(crisps);
        items.add(coke);
        items.add(coke);
        items.add(coke);
        items.add(coke);


        // run this over 10 random baskets
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(items);
            setUp();
            for (Item item : items) {
                cart.addItem(item);
            }
            String receipt = checkout.generateReceipt(cart);
            if (!receipt.contains("TOTAL      £  4.25")) {
                fail("The total value is wrong:\n" + receipt);
            }
        }
    }


}
