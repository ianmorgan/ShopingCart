package com.example.discounts;

import com.example.Item;

import java.util.*;

/**
 *
 */
public class FreeWithOtherItemsDiscounter implements Discounter {

    // it is possible for multiple  instances of this offer to be running.
    List<State> runningOffers = new ArrayList<State>();

    public Discount applyDiscount(Item item) {
        Discount result = null;
        if (item.discountOffer() instanceof FreeWithOtherItemsOffer) {
            FreeWithOtherItemsOffer offer = (FreeWithOtherItemsOffer) item.discountOffer();


            if (offer.isPrimaryItem(item)) {
                // If its primary we are simply recording it
                State state = current();

                if (state.primary.size() < offer.numberToBuy()) {
                    state.primary.add(item);
                } else {
                    State next = new State();
                    next.primary.add(item);
                    runningOffers.add(next);
                }
            }
            else {
                State state = current();

            }


//
//                state.primary.add(item);
//            } else {
//                state.secondary.add(item);
//            }
//
//
//            if (state.primary.size() == offer.numberToBuy()) {
//                result = calculateDiscount(state, offer);
//
//                if (offerExhausted(state, offer)) {
//                    runningOffers.remove(0);
//                }
//
//            }

        }

        return result;
    }

    private boolean offerExhausted(State state, FreeWithOtherItemsOffer offer) {
        return state.numberApplied == offer.numberFree();
    }

    private State current() {
        if (runningOffers.size() == 0) {
            runningOffers.add(new State());
        }

        // the tail
        return runningOffers.get(runningOffers.size() - 1);

    }

    private Discount calculateDiscount(State state, FreeWithOtherItemsOffer offer) {
        if (state.secondary.size() > 0) {
            // the simplistic rule
            double discount = 0;
            for (Item item : state.secondary) {
                if (state.numberApplied < offer.numberFree()) {
                    discount += item.price();
                }
            }

            return new Discount(state.secondary.get(0), offer, discount);
        }
        return null;
    }

    private class State {
        List<Item> primary = new ArrayList<Item>();
        List<Item> secondary = new ArrayList<Item>();
        int numberApplied = 0;
    }


}
