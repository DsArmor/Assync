package com.example.assync;

import com.example.assync.interfaces.IProduct;

import java.util.ArrayList;
import java.util.List;

public class Automate {
    List<IProduct> product = new ArrayList<>();
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
//    public IProduct getProduct(){
//        return ;
//    }
}
