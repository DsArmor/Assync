package com.example.assync.product;

import com.example.assync.interfaces.IProduct;

public class Shawarma implements IProduct {
    private String name="Shawarma";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int cost() {
        return 120;
    }
}
