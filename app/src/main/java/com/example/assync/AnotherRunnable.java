package com.example.assync;

import android.os.Handler;

import java.util.concurrent.TimeUnit;


public class AnotherRunnable implements Runnable{
    Automate automate;
    Handler handler;

    public int Choose(Automate automate){
        String type = automate.getName();
        return Integer.parseInt(type);
    }

    public AnotherRunnable(Automate automate, Handler handler){
        this.automate = automate;
        this.handler=handler;
    }
    @Override
    public void run() {
        int i = Choose(automate);
        for (Student student : automate.getQueue()){
            automate.current_student=student.getNumber();
            automate.status= Status.Reception;
            handler.sendEmptyMessage(i);
            int temp = (int)(Math.random()*3+1);
            try {
                TimeUnit.SECONDS.sleep(temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = student.choose_product(automate);
            if (flag){
                automate.status = Status.Payment;
                handler.sendEmptyMessage(i);
                student.buy_product(automate);

                automate.status = Status.Delivery;
                handler.sendEmptyMessage(i);
                automate.issue();
                automate.current_student=0;

            }
            handler.sendEmptyMessage(i);
            automate.issue();
            automate.current_student=0;
            handler.sendEmptyMessage(i);
        }
    }
}