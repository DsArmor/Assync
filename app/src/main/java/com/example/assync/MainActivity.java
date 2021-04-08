package com.example.assync;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.assync.singltone.Courier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends FragmentActivity {

    EditText count_of_products;
    ProgressBar progressBar;

    Button add;
    Button start;

    String[] list_of_products = {"CocaCola", "Pepsi", "Popcorn", "Shawarma"};
    String[] list_of_automates = {"1", "2", "3", "4"};

    Automate automate1 = new Automate();
    Automate automate2 = new Automate();
    Automate automate3 = new Automate();
    Automate automate4 = new Automate();
    Student[] students = new Student[20];

    List<Student> queue1 = new ArrayList<>();
    List<Student> queue2 = new ArrayList<>();
    List<Student> queue3 = new ArrayList<>();
    List<Student> queue4 = new ArrayList<>();

    VendingMachineFragment fragment1 = VendingMachineFragment.newInstance();
    VendingMachineFragment fragment2 = VendingMachineFragment.newInstance();
    VendingMachineFragment fragment3 = VendingMachineFragment.newInstance();
    VendingMachineFragment fragment4 = VendingMachineFragment.newInstance();

    Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    fragment1.status1.setText(automate1.status.toString());
                    fragment1.students1.setText(String.valueOf(automate1.current_student));
                    if (fragment1.status1.getText().equals("Payment")) {
                        fragment1.sum1.setText(String.valueOf(automate1.getEarnings()));
                        fragment1.products1.setText(automate1.toString());
                    }
                    break;
                case 2:
                    fragment2.status1.setText(automate2.status.toString());

                    fragment2.students1.setText(String.valueOf(automate2.current_student));
                    if (fragment2.status1.getText().equals("Payment")) {
                        fragment2.sum1.setText(String.valueOf(automate2.getEarnings()));
                        fragment2.products1.setText(automate2.toString());
                    }
                    break;
                case 3:
                    fragment3.status1.setText(automate3.status.toString());
                    fragment3.students1.setText(String.valueOf(automate3.current_student));
                    if (fragment3.status1.getText().equals("Payment")) {
                        fragment3.sum1.setText(String.valueOf(automate3.getEarnings()));
                        fragment3.products1.setText(automate3.toString());
                    }
                    break;
                case 4:
                    fragment4.status1.setText(automate4.status.toString());
                    fragment4.students1.setText(String.valueOf(automate4.current_student));
                    if (fragment4.status1.getText().equals("Payment")) {
                        fragment4.sum1.setText(String.valueOf(automate4.getEarnings()));
                        fragment4.products1.setText(automate4.toString());
                    }
                    break;
            }
        }
    };

    void putStudents(Student[] students){
        int i=1;
        for (Student student: students){
            student = new Student();
            int rand_choose = (int) (Math.random() * 4);
            student.setNumber(i);
            i++;
            switch (rand_choose){
                case 0:
                    queue1.add(student);
                    break;
                case 1:
                    queue4.add(student);
                    break;
                case 2:
                    queue2.add(student);
                    break;
                case 3:
                    queue3.add(student);
            }
        }
        automate1.setQueue(queue1);
        automate2.setQueue(queue2);
        automate3.setQueue(queue3);
        automate4.setQueue(queue4);
    }
    Automate current_automate(String automate){
        switch (automate){
            case "1":
                return automate1;
            case "2":
                return automate2;
            case "3":
                return automate3;
            case "4":
                return automate4;
        }
        return null;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.frame1, fragment1);
        transaction.add(R.id.frame2, fragment2);
        transaction.add(R.id.frame3, fragment3);
        transaction.add(R.id.frame4, fragment4);
        transaction.commit();

        Courier courier = Courier.getInstance();

        count_of_products = findViewById(R.id.count_of_products);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        add = (Button)findViewById(R.id.add_products);
        start = (Button)findViewById(R.id.start_game);

        //обработка spinners для продуктов и автоматов
        ArrayAdapter<String> adapter_of_automates= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_of_automates);
        adapter_of_automates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter_of_products = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_of_products);
        adapter_of_products.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner_of_automates = (Spinner) findViewById(R.id.spinner_of_automates);
        spinner_of_automates.setAdapter(adapter_of_automates);

        Spinner spinner_of_products = (Spinner) findViewById(R.id.spinner_of_products);
        spinner_of_products.setAdapter(adapter_of_products);

        automate1.setName("1");
        automate2.setName("2");
        automate3.setName("3");
        automate4.setName("4");

        //Загрузка продуктов в автомат
        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String selected_product= spinner_of_products.getSelectedItem().toString();
                String selected_automate = spinner_of_automates.getSelectedItem().toString();

                System.out.println(count_of_products.getText().toString());
                System.out.println(selected_product);
                System.out.println(selected_automate);

                courier.putProduct(selected_product, current_automate(selected_automate), Integer.parseInt(count_of_products.getText().toString()));
                fragment1.products1.setText(automate1.toString());
                fragment2.products1.setText(automate1.toString());
                fragment3.products1.setText(automate1.toString());
                fragment4.products1.setText(automate1.toString());

            }
        });
        putStudents(students);
        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                fragment1.status1.setText(automate1.status.toString());
                fragment2.status1.setText(automate1.status.toString());
                fragment3.status1.setText(automate1.status.toString());
                fragment4.status1.setText(automate1.status.toString());

                progressBar.setVisibility(View.VISIBLE);

                Thread thread1=new Thread(new AnotherRunnable(automate1, handler));
                Thread thread2=new Thread(new AnotherRunnable(automate2, handler));
                Thread thread3=new Thread(new AnotherRunnable(automate3, handler));
                Thread thread4=new Thread(new AnotherRunnable(automate4, handler));

                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
            }
        });
    }

    class AnotherRunnable implements Runnable{
        private Automate automate;
        private Handler handler;


        public AnotherRunnable(Automate vendingMachine, Handler handler) {
            this.automate = vendingMachine;
            this.handler = handler;
        }

        public int Choose(Automate automate){
            String type = automate.getName();
            return Integer.parseInt(type);
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
}