package com.example.assync.delivery;

import com.example.assync.interfaces.IDelivery;
import com.example.assync.interfaces.IProduct;
import com.example.assync.product.Shawarma;

public class DeliveryShawarma implements IDelivery {
    @Override
    public IProduct Delivery() {
        return new Shawarma();
    }
}
