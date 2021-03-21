package com.example.assync;

import com.example.assync.delivery.DeliveryCocaCola;
import com.example.assync.delivery.DeliveryPepsi;
import com.example.assync.delivery.DeliveryPopcorn;
import com.example.assync.delivery.DeliveryShawarma;
import com.example.assync.interfaces.IProduct;
import com.example.assync.product.CocaCola;

import java.util.ArrayList;
import java.util.List;

public class Automate {
    private List<IProduct> CocaCola = new ArrayList<>();
    private List<IProduct> Pepsi = new ArrayList<>();
    private List<IProduct> Popcorn = new ArrayList<>();
    private List<IProduct> Shawarma = new ArrayList<>();
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
    DeliveryCocaCola delCoca= new DeliveryCocaCola();
    DeliveryPepsi delPepsi = new DeliveryPepsi();
    DeliveryPopcorn delPopcorn = new DeliveryPopcorn();
    DeliveryShawarma delShawarma = new DeliveryShawarma();
    public void getProduct(String product){
        switch (product){
            case "CocaCola":
                CocaCola.add(delCoca.Delivery());
                break;
            case "Pepsi":
                Pepsi.add(delPepsi.Delivery());
                break;
            case "Popcorn":
                Popcorn.add(delPopcorn.Delivery());
            case "Shawarma":
                Shawarma.add(delShawarma.Delivery());
        }
    }
    //TODO функции для возвращения количества тех или иных товаров
    //TODO функции для возможности удаления тех или иных товаров
}
