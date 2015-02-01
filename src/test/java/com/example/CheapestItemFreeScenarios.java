package com.example;

import com.example.discounts.CheapestItemFreeOffer;
import org.testng.annotations.Test;


@Test
public class CheapestItemFreeScenarios extends BaseCheckoutScenarios {
    private CheapestItemFreeOffer offer1 = new CheapestItemFreeOffer();
    private CheapestItemFreeOffer offer2 = new CheapestItemFreeOffer();

    @Test
    public void cheapestMatchingItemIsFree() {
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Sugar", 1.19, offer1));
        cart.addItem(new Item("Tea", 2.01, offer1));

        assertReceipt("Coffee     £  3.99\n" +
                "Sugar      £  1.19\n" +
                "Tea        £  2.01\n" +
                "DISCOUNTS\n" +
                "Sugar      £ -1.19 (Cheapest free)\n" +
                "TOTAL      £  6.00");
    }

    @Test
    public void noDiscountFor4thMatchingItem() {
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Sugar", 1.19, offer1));
        cart.addItem(new Item("Tea", 2.01, offer1));
        cart.addItem(new Item("Biscuits", 0.99, offer1));


        assertReceipt("Coffee     £  3.99\n" +
                "Sugar      £  1.19\n" +
                "Tea        £  2.01\n" +
                "Biscuits   £  0.99\n" +
                "DISCOUNTS\n" +
                "Sugar      £ -1.19 (Cheapest free)\n" +
                "TOTAL      £  6.99");
    }

    @Test
    public void multipleDiscountsPermitted() {
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Sugar", 1.19, offer1));
        cart.addItem(new Item("Beer", 10.00, offer2));
        cart.addItem(new Item("Wine", 15.00, offer2));
        cart.addItem(new Item("Gin", 20.00, offer2));
        cart.addItem(new Item("Tea", 2.01, offer1));

        assertReceipt("Coffee     £  3.99\n" +
                "Sugar      £  1.19\n" +
                "Beer       £ 10.00\n" +
                "Wine       £ 15.00\n" +
                "Gin        £ 20.00\n" +
                "Tea        £  2.01\n" +
                "DISCOUNTS\n" +
                "Beer       £ -10.00 (Cheapest free)\n" +
                "Sugar      £ -1.19 (Cheapest free)\n" +
                "TOTAL      £ 41.00");
    }

}
