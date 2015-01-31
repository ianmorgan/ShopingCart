package com.example;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


/**
 * The simple examples for shopping carts without any special
 * prices applied.
 */

@Test
public class SimpleCheckoutScenarios extends BaseCheckoutScenarios {

    public void checkoutASingleItem() {
        cart.addItem(new Item("Milk", 0.99));

        assertReceipt("Milk       £  0.99\n" +
                "TOTAL      £  0.99");
    }

    public void checkoutTwoDifferentItems() {
        cart.addItem(new Item("Milk", 0.99));
        cart.addItem(new Item("Bread", 1.58));

        assertReceipt("Milk       £  0.99\n" +
                "Bread      £  1.58\n" +
                "TOTAL      £  2.57");
    }

    public void checkoutMultipleIdenticalItems() {
        cart.addItem(new Item("Milk", 0.99));
        cart.addItem(new Item("Milk", 0.99));
        cart.addItem(new Item("Milk", 0.99));

        assertReceipt("Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "TOTAL      £  2.97");
    }


}

