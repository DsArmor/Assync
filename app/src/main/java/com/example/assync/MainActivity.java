package com.example.assync;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
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

    //сделать так, чтобы при нажатии автомат открывался на весь экран

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

        //поиграемся с фрагментами
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();

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
//                products1.setText(automate1.toString());

            }
        });
        putStudents(students);
        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                status1.setText(automate1.status.toString());

                progressBar.setVisibility(View.VISIBLE);

                automate1.thread.start();
                automate2.thread.start();
                automate3.thread.start();
                automate4.thread.start();
            }
        });
    }


}