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
import com.example.assync.product.Pepsi;
import com.example.assync.product.Popcorn;
import com.example.assync.product.Shawarma;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Student {

    private int number;

    public void setNumber(int number){
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean choose_product(Automate automate){
        Map<IProduct, Integer> products = automate.inStock();

        boolean flag1 = false;
        boolean flag2 = false;
        int i=0;
        while (!(flag1 && flag2) && i<1000){
            int temp1 = (int)(Math.random()*2);
            int temp2 = (int)(Math.random()*2);
            if (i>0){
                if (flag1){
                    List<IDelivery> temp = new ArrayList<>();
                    switch (temp1){
                        case 0:
                            temp.add(new DeliveryCocaCola());
                            automate.getProduct(temp);
                            break;
                        case 1:
                            temp.add(new DeliveryPepsi());
                            automate.getProduct(temp);
                            break;
                    }
                }
                if (flag2){
                    List<IDelivery> temp = new ArrayList<>();
                    switch (temp2){
                        case 0:
                            temp.add(new DeliveryPopcorn());
                            automate.getProduct(temp);
                            break;
                        case 1:
                            temp.add(new DeliveryShawarma());
                            automate.getProduct(temp);
                            break;
                    }
                }
            }
            switch (temp1){
                case 0:
                    flag1 = automate.BuyProduct(new CocaCola());
                    break;
                case 1:
                    flag1 = automate.BuyProduct(new Pepsi());
                    break;
            }
            switch (temp2){
                case 0:
                    flag2 = automate.BuyProduct(new Popcorn());
                    break;
                case 1:
                    flag2 = automate.BuyProduct(new Shawarma());
                    break;
            }
            i++;
        }

        int temp = (int)(Math.random()*5+1);
        try {
            TimeUnit.SECONDS.sleep(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        return (i < 1000);

    }
    public void buy_product(Automate automate){
        int temp = (int)(Math.random()*5+1);
        try {
            TimeUnit.SECONDS.sleep(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
