package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ianmorgan on 31/01/15.
 */
public class Cart {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Iterable<Item> items(){
        return items;
    }
}
