package com.example.assync.product;

import com.example.assync.interfaces.IProduct;

public class Pepsi implements IProduct {
    private String name="Pepsi";

    @Override
    public String getName() {
        return name;
    }
}
