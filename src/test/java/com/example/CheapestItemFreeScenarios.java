package com.example;

import com.example.discounts.CheapestItemFreeOffer;
import com.example.discounts.TwoAtSpecialPriceOffer;
import org.testng.annotations.Test;

/**
 * Created by ianmorgan on 1/02/15.
 */
@Test
public class CheapestItemFreeScenarios extends BaseCheckoutScenarios {
    private CheapestItemFreeOffer offer = new CheapestItemFreeOffer();


    @Test
    public void cheapestMatchingItemIsFree() {
        cart.addItem(new Item("Coffee", 3.99, offer));
        cart.addItem(new Item("Sugar", 1.19, offer));
        cart.addItem(new Item("Tea", 2.01, offer));


        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "DISCOUNTS\n" +
                "Coffee     £ -1.98 (Buy 2 offer)\n" +
                "TOTAL      £  6.00");
    }

}
