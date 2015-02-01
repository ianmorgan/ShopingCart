package com.example;

import com.example.discounts.ThreeForTwoDiscounter;
import com.example.discounts.ThreeForTwoOffer;
import org.testng.annotations.Test;

@Test
public class ThreeForTwoScenarios extends BaseCheckoutScenarios {
    
    private ThreeForTwoOffer offer = new ThreeForTwoOffer();

    @Test
    public void discountForThreeItemsTheSame() {
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));

        assertReceipt("Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "DISCOUNTS\n" +
                "Milk       £ -0.99 (3 for 2)\n" +
                "TOTAL      £  1.98");
    }

    @Test
    public void noDiscountForTwoItemsTheSame() {
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));

        assertReceipt("Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "TOTAL      £  1.98");
    }

    @Test
    public void multipleDiscountsPermitted() {
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Coffee", 2.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));
        cart.addItem(new Item("Coffee", 2.99, offer));
        cart.addItem(new Item("Coffee", 2.99, offer));
        cart.addItem(new Item("Milk", 0.99, offer));

        assertReceipt("Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "Coffee     £  2.99\n" +
                "Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "Milk       £  0.99\n" +
                "Coffee     £  2.99\n" +
                "Coffee     £  2.99\n" +
                "Milk       £  0.99\n" +
                "DISCOUNTS\n" +
                "Milk       £ -0.99 (3 for 2)\n" +
                "Coffee     £ -2.99 (3 for 2)\n" +
                "Milk       £ -0.99 (3 for 2)\n" +
                "TOTAL      £  9.94");
    }

}
