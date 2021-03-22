package com.example.assync.product;

import com.example.assync.interfaces.IProduct;

public class Popcorn implements IProduct {
    private String name="Popcorn";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int cost() {
        return 400;
    }
    @Override
    public int compareTo(IProduct product) {
        return getName().compareTo(product.getName());
    }
}
