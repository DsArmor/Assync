package com.example.assync.delivery;

import com.example.assync.interfaces.IDelivery;
import com.example.assync.interfaces.IProduct;
import com.example.assync.product.Popcorn;

public class DeliveryPopcorn implements IDelivery {
    @Override
    public IProduct Delivery() {
        return new Popcorn();
    }
}
