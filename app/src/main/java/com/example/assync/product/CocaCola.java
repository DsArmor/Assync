package com.example.assync.product;

import com.example.assync.interfaces.IProduct;

public class CocaCola implements IProduct {
    private String name="CocaCola";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int cost() {
        return 50;
    }

    @Override
    public int compareTo(IProduct product) {
        return getName().compareTo(product.getName());
    }
}
