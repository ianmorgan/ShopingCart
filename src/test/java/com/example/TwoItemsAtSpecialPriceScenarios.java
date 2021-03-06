package com.example;

import com.example.discounts.twoAtSpecialPrice.TwoAtSpecialPriceOffer;
import org.testng.annotations.Test;


@Test
public class TwoItemsAtSpecialPriceScenarios extends BaseCheckoutScenarios {

    private TwoAtSpecialPriceOffer offer1 = new TwoAtSpecialPriceOffer("Managers Special Offer", 1.98);
    private TwoAtSpecialPriceOffer offer2 = new TwoAtSpecialPriceOffer("Save a penny!", 0.01);


    @Test
    public void specialPriceForTwoItemsTheSame() {
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Coffee", 3.99, offer1));

        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "DISCOUNTS\n" +
                "Coffee     £ -1.98 (Managers Special Offer)\n" +
                "TOTAL      £  6.00");
    }

    @Test
    public void noSpecialPriceForTwoItemsInDifferentOffers() {
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Coffee", 3.99, offer2));

        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "TOTAL      £  7.98");
    }

    @Test
    public void noSpecialPriceForThirdMatchingItem() {
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Coffee", 3.99, offer1));

        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "DISCOUNTS\n" +
                "Coffee     £ -1.98 (Managers Special Offer)\n" +
                "TOTAL      £  9.99");
    }

    @Test
    public void multipleSpecialPriceOffersPermitted() {
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Milk", 0.50, offer2));
        cart.addItem(new Item("Coffee", 3.99, offer1));
        cart.addItem(new Item("Milk", 0.50, offer2));
        cart.addItem(new Item("Coffee", 3.99, offer1));

        assertReceipt("Coffee     £  3.99\n" +
                "Coffee     £  3.99\n" +
                "Milk       £  0.50\n" +
                "Coffee     £  3.99\n" +
                "Milk       £  0.50\n" +
                "Coffee     £  3.99\n" +
                "DISCOUNTS\n" +
                "Coffee     £ -1.98 (Managers Special Offer)\n" +
                "Milk       £ -0.01 (Save a penny!)\n" +
                "Coffee     £ -1.98 (Managers Special Offer)\n" +
                "TOTAL      £ 12.99");
    }

}
