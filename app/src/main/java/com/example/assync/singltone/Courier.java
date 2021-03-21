package com.example.assync.singltone;

import com.example.assync.Automate;
import com.example.assync.interfaces.IProduct;

public class Courier {
    private Courier(){}
    private Courier instance=null;
    public Courier getInstance(){
        if (instance==null){
            instance=new Courier();
        }
        return instance;
    }
    //TODO сделай так, чтобы курьер принимал автомат, тип продукта и накладывал в него товары соответсвующие имени
    public void putProduct(String product, Automate automate){

    }
}
