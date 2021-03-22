package com.example.assync;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.assync.delivery.DeliveryCocaCola;
import com.example.assync.delivery.DeliveryPepsi;
import com.example.assync.delivery.DeliveryPopcorn;
import com.example.assync.delivery.DeliveryShawarma;
import com.example.assync.interfaces.IDelivery;
import com.example.assync.interfaces.IProduct;
import com.example.assync.product.CocaCola;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Automate {
//    private List<IProduct> CocaCola = new ArrayList<>();
//    private List<IProduct> Pepsi = new ArrayList<>();
//    private List<IProduct> Popcorn = new ArrayList<>();
//    private List<IProduct> Shawarma = new ArrayList<>();
    public String idleTime(){
        return "idle time";
    }
    public String reception(){
        return "reception";
    }
    public String payment(){
        return "payment";
    }
    public String issue(){
        return "issue";
    }

    Map<IProduct, Integer> products= new TreeMap<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getProduct(List<IDelivery> deliveries){
        for (IDelivery current : deliveries){
            IProduct product = current.Delivery();
            int count = products.getOrDefault(product, 0);
            products.put(product, count+1);
        }
    }
    //TODO функции для возможности удаления тех или иных товаров
    @RequiresApi(api = Build.VERSION_CODES.N)
    boolean BuyProduct(IProduct product) {
        int count = products.getOrDefault(product, 0);
        if (count > 0) {
            products.put(product, count - 1);
            return true;
        } else {
            return false;
        }
    }
    //TODO функции для возвращения количества тех или иных товаров
    @Override
    public String toString() {
        String out = "";

        TreeSet<IProduct> keys = new TreeSet<>(products.keySet());

        for (IProduct product : keys) {
            out += product.getName() + " : " +products.get(product) + "\n";
        }

        return out;
    }


}
