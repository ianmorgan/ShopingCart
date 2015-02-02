package com.example;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

/**
 * Common code to simplify scenario writing
 */
public class BaseCheckoutScenarios {
    protected Cart cart;
    protected Checkout checkout;

    @BeforeMethod
    public void setUp() {
        cart = new Cart();
        checkout = new Checkout();
    }

    protected void assertReceipt(String expected) {
        // From the checkout results we can generate an example text receipt
        // which is easy to read and verify in scenarios.
        //
        // In this example the logic is mostly relying upon toString() methods. In production code this would
        // of course be broken into a more formal model / view layer,
        // allowing different type of presentation to be injected in.

        String receipt = checkout.generateReceipt(cart);
        assertEquals(receipt, expected);
    }
}
