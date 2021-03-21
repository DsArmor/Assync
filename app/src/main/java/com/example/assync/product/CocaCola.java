package com.example.assync.product;

import com.example.assync.interfaces.IProduct;

public class CocaCola implements IProduct {
    private String name="CocaCola";

    @Override
    public String getName() {
        return name;
    }
}
