package com.example.assync;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.assync.interfaces.IDelivery;
import com.example.assync.interfaces.IProduct;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Automate {
    //TODO функцию, которая спрашивает о том, какой студент к автомату подошел
    private String name;
    public Status status = Status.idle_time;
    private List<Student> queue;

    public void issue(){
        status = Status.DELIVERY;
        int temp=(int)(Math.random()*3+1);
        try {
            TimeUnit.SECONDS.sleep(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status = Status.idle_time;
    }

    private final Map<IProduct, Integer> products= new TreeMap<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getProduct(List<IDelivery> deliveries){
        for (IDelivery current : deliveries){
            IProduct product = current.Delivery();
            int count = products.getOrDefault(product, 0);
            products.put(product, count+1);
        }
    }

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

    public Map<IProduct, Integer> inStock(){
        return products;
    }

    @Override
    public String toString() {
        String out = "";

        TreeSet<IProduct> keys = new TreeSet<>(products.keySet());

        for (IProduct product : keys) {
            out += product.getName() + " : " +products.get(product) + "\n";
        }

        return out;
    }

    public List<Student> getQueue() {
        return queue;
    }
    public void setQueue(List<Student> queue){
        this.queue = queue;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}

enum Status{
    idle_time,
    reception,
    payment,
    DELIVERY;
}
