package com.example;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

/**
 * Common code to simplify test cases
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
        String receipt = checkout.generateReceipt(cart);
        assertEquals(receipt, expected);
    }
}
