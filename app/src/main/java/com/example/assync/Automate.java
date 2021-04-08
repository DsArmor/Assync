package com.example.assync;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.assync.interfaces.IDelivery;
import com.example.assync.interfaces.IProduct;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Automate{

    private String name;
    private int earnings=0;
    public Status status = Status.Idle_time;
    private List<Student> queue;
    public int current_student;
    public VendingMachineFragment fragment;
    public Thread thread;

    public Automate(){
        thread = new Thread(new AnotherRunnable(this, handler));
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
//            updateView();
        }
    };

    public void updateView() {
        if (fragment != null)
            fragment.updateView();
//        if (bigFragment != null)
//            bigFragment.updateView();
    }

    public void issue(){
        int temp=(int)(Math.random()*3+1);
        try {
            TimeUnit.SECONDS.sleep(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status = Status.Idle_time;
    }

    private final Map<IProduct, Integer> products= new TreeMap<>();
    public void getProduct(List<IDelivery> deliveries){
        for (IDelivery current : deliveries){
            IProduct product = current.Delivery();
            int count = products.getOrDefault(product, 0);
            products.put(product, count+1);
        }
    }

    boolean BuyProduct(IProduct product) {
        int count = products.getOrDefault(product, 0);
        if (count > 0) {
            earnings+=product.cost();
            products.put(product, count - 1);
            return true;
        } else {
            return false;
        }
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
    public Map<IProduct, Integer> inStock(){
        return products;
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
    public void setEarnings(int cost){
        this.earnings-=cost;
    }
    public int getEarnings(){
        return this.earnings;
    }
}
enum Status{
    Idle_time,
    Reception,
    Payment,
    Delivery;
}


