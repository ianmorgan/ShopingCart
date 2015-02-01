package com.example;

import org.testng.annotations.Test;

@Test
public class TwoForThreeScenarios extends BaseCheckoutScenarios {

    public void checkoutThreeItemsTheSame() {
        cart.addItem(new Item("Milk", 0.99, Item.DiscountRule.TwoForThree));
        cart.addItem(new Item("Milk", 0.99, Item.DiscountRule.TwoForThree));
        cart.addItem(new Item("Milk", 0.99, Item.DiscountRule.TwoForThree));

        assertReceipt("Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "DISCOUNTS\n" +
                "Milk       £ -0.99\n (2 for 3)\n" +
                "TOTAL      £  1.98");
    }

}
