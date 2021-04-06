package com.example.assync;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
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

    Handler handler;

    TextView machine1;
    TextView status1;
    TextView sum1;
    TextView products1;
    TextView students1;

    TextView machine2;
    TextView status2;
    TextView sum2;
    TextView products2;
    TextView students2;

    TextView machine3;
    TextView status3;
    TextView sum3;
    TextView products3;
    TextView students3;

    TextView machine4;
    TextView status4;
    TextView sum4;
    TextView products4;
    TextView students4;

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


        Courier courier = Courier.getInstance();

        //инициализация всего xml
        machine1 = (TextView)findViewById(R.id.machine1);
        machine1.setText("First automate");
        status1 = (TextView)findViewById(R.id.status1);
        sum1 = (TextView)findViewById(R.id.sum1);
        products1 = (TextView)findViewById(R.id.products1);
        students1 = (TextView)findViewById(R.id.students1);

        machine2 = (TextView)findViewById(R.id.machine2);
        machine2.setText("Second automate");
        status2 = (TextView)findViewById(R.id.status2);
        sum2 = (TextView)findViewById(R.id.sum2);
        products2 = (TextView)findViewById(R.id.products2);
        students2 = (TextView)findViewById(R.id.students2);

        machine3 = (TextView)findViewById(R.id.machine3);
        machine3.setText("Third automate");
        status3 = (TextView)findViewById(R.id.status3);
        sum3 = (TextView)findViewById(R.id.sum3);
        products3 = (TextView)findViewById(R.id.products3);
        students3 = (TextView)findViewById(R.id.students3);

        machine4 = (TextView)findViewById(R.id.machine4);
        machine4.setText("Fourth automate");
        status4 = (TextView)findViewById(R.id.status4);
        sum4 = (TextView)findViewById(R.id.sum4);
        products4 = (TextView)findViewById(R.id.products4);
        students4 = (TextView)findViewById(R.id.students4);

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
                products1.setText(automate1.toString());
                products2.setText(automate2.toString());
                products3.setText(automate3.toString());
                products4.setText(automate4.toString());
            }
        });
        putStudents(students);
        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                status1.setText(automate1.status.toString());
                status2.setText(automate2.status.toString());
                status3.setText(automate3.status.toString());
                status4.setText(automate4.status.toString());
                progressBar.setVisibility(View.VISIBLE);

                handler = new Handler(){
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 1:
                                status1.setText(automate1.status.toString());
                                students1.setText(String.valueOf(automate1.current_student));
                                if (status1.getText().equals("Payment")) {
                                    sum1.setText(String.valueOf(automate1.getEarnings()));
                                    products1.setText(automate1.toString());

                                }
                                break;
                            case 2:
                                status2.setText(automate2.status.toString());

                                students2.setText(String.valueOf(automate2.current_student));
                                if (status2.getText().equals("Payment")) {
                                    sum2.setText(String.valueOf(automate2.getEarnings()));
                                    products2.setText(automate2.toString());
                                }
                                break;
                            case 3:
                                status3.setText(automate3.status.toString());

                                students3.setText(String.valueOf(automate3.current_student));
                                if (status3.getText().equals("Payment")) {
                                    sum3.setText(String.valueOf(automate3.getEarnings()));
                                    products3.setText(automate3.toString());
                                }
                                break;
                            case 4:
                                status4.setText(automate4.status.toString());
                                students4.setText(String.valueOf(automate4.current_student));
                                if (status4.getText().equals("Payment")) {
                                    sum4.setText(String.valueOf(automate4.getEarnings()));
                                    products4.setText(automate4.toString());
                                }
                                break;
                        }
                    }
                };

                Thread thread1=new Thread(new AnotherRunnable(automate1));
                Thread thread2=new Thread(new AnotherRunnable(automate2));
                Thread thread3=new Thread(new AnotherRunnable(automate3));
                Thread thread4=new Thread(new AnotherRunnable(automate4));

                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
            }
        });
    }

    class AnotherRunnable implements Runnable{
        Automate automate;

        public int Choose(Automate automate){
            String type = automate.getName();
            return Integer.parseInt(type);
        }

        public AnotherRunnable(Automate automate){
            this.automate = automate;
        }
        @Override
        public void run() {
            int i = Choose(automate);
            for (Student student : automate.getQueue()){
                automate.current_student=student.getNumber();
                automate.status= Status.Reception;
                handler.sendEmptyMessage(i);
                //здесь что-то нужно вернуть в основной поток
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
                    //здесь что-то нужно вернуть в основной поток
                    student.buy_product(automate);

                    automate.status = Status.Delivery;
                    handler.sendEmptyMessage(i);
                    //здесь что-то нужно вернуть в основной поток
                    automate.issue();
                    automate.current_student=0;

                }
                handler.sendEmptyMessage(i);
                //здесь что-то нужно вернуть в основной поток
                automate.issue();
                automate.current_student=0;
                handler.sendEmptyMessage(i);
                //здесь что-то нужно вернуть в основной поток
            }
        }
    }
}