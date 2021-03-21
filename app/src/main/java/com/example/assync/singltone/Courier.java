package com.example.assync.singltone;

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

    public void putProduct(IProduct product){

    }
}
