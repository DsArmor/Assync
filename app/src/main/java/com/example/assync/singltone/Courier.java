package com.example.assync.singltone;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.assync.Automate;
import com.example.assync.delivery.DeliveryCocaCola;
import com.example.assync.delivery.DeliveryPepsi;
import com.example.assync.delivery.DeliveryPopcorn;
import com.example.assync.delivery.DeliveryShawarma;
import com.example.assync.interfaces.IDelivery;
import com.example.assync.interfaces.IProduct;

import java.util.ArrayList;
import java.util.List;

public class Courier {
    private Courier(){}
    private static Courier instance=null;
    public static Courier getInstance(){
        if (instance==null){
            instance=new Courier();
        }
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void putProduct(String product, Automate automate, int count){
        List<IDelivery> deliveries = new ArrayList<>();
        for (int i=0; i<count; i++){
            switch (product){
                case "CocaCola":
                    deliveries.add(new DeliveryCocaCola());
                    break;
                case "Pepsi":
                    deliveries.add(new DeliveryPepsi());
                    break;
                case "Popcorn":
                    deliveries.add(new DeliveryPopcorn());
                    break;
                case "Shawarma":
                    deliveries.add(new DeliveryShawarma());
            }
        }
        automate.getProduct(deliveries);
    }
}
