package com.example.assync.delivery;

import com.example.assync.interfaces.IDelivery;
import com.example.assync.interfaces.IProduct;
import com.example.assync.product.CocaCola;

public class DeliveryCocaCola implements IDelivery {
    @Override
    public IProduct Delivery() {
        return new CocaCola();
    }
}
