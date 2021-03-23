package com.example.assync;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

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

        Courier courier = Courier.getInstance();

        //инициализация всего xml
        machine1 = (TextView)findViewById(R.id.machine1);
        machine1.setText("First automate");
        status1 = (TextView)findViewById(R.id.status1);
        sum1 = (TextView)findViewById(R.id.sum_1);
        products1 = (TextView)findViewById(R.id.products_1);
        students1 = (TextView)findViewById(R.id.students_1);

        machine2 = (TextView)findViewById(R.id.machine2);
        machine2.setText("Second automate");
        status2 = (TextView)findViewById(R.id.status2);
        sum2 = (TextView)findViewById(R.id.sum_2);
        products2 = (TextView)findViewById(R.id.products_2);
        students2 = (TextView)findViewById(R.id.students_2);

        machine3 = (TextView)findViewById(R.id.machine3);
        machine3.setText("Third automate");
        status3 = (TextView)findViewById(R.id.status3);
        sum3 = (TextView)findViewById(R.id.sum_3);
        products3 = (TextView)findViewById(R.id.products_3);
        students3 = (TextView)findViewById(R.id.students_3);

        machine4 = (TextView)findViewById(R.id.machine4);
        machine4.setText("Fourth automate");
        status4 = (TextView)findViewById(R.id.status4);
        sum4 = (TextView)findViewById(R.id.sum_4);
        products4 = (TextView)findViewById(R.id.products_4);
        students4 = (TextView)findViewById(R.id.students_4);

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
                progressBar.setVisibility(View.VISIBLE);
                StreamAutomate streamAutomate1 = new StreamAutomate();
                StreamAutomate streamAutomate2 = new StreamAutomate();
                StreamAutomate streamAutomate3 = new StreamAutomate();
                StreamAutomate streamAutomate4 = new StreamAutomate();

                streamAutomate1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, automate1);
                streamAutomate2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, automate2);
                streamAutomate3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, automate3);
                streamAutomate4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, automate4);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    class StreamAutomate extends AsyncTask<Automate, Automate, Void> {

        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground(Automate... automates) {
            for (Student student : automates[0].getQueue()){
                automates[0].current_student=student.getNumber();
                automates[0].status= com.example.assync.Status.Reception;
                publishProgress(automates[0]);
                int temp = (int)(Math.random()*3+1);
                try {
                    TimeUnit.SECONDS.sleep(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(automates[0]);
                boolean flag = student.choose_product(automates[0]);
                if (flag){
                    automates[0].status = com.example.assync.Status.Payment;
                    publishProgress(automates[0]);
                    student.buy_product(automates[0]);

                    automates[0].status = com.example.assync.Status.Delivery;
                    publishProgress(automates[0]);
                    automates[0].issue();
                    automates[0].current_student=0;

                }
                publishProgress(automates[0]);
                automates[0].issue();
                automates[0].current_student=0;
                publishProgress(automates[0]);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Automate... automates) {
            super.onProgressUpdate(automates);
            switch (automates[0].getName()){
                case "1":
                    status1.setText(automates[0].status.toString());
                    products1.setText(automates[0].toString());
                    students1.setText(String.valueOf(automates[0].current_student));
                    if (status1.getText().equals("Payment")){
                        sum1.setText(String.valueOf(automates[0].getEarnings()));
                    }
                    break;
                case "2":
                    status2.setText(automates[0].status.toString());
                    products2.setText(automates[0].toString());
                    students2.setText(String.valueOf(automates[0].current_student));
                    if (status2.getText().equals("Payment")){
                        sum2.setText(String.valueOf(automates[0].getEarnings()));
                    }
                    break;
                case "3":
                    status3.setText(automates[0].status.toString());
                    products3.setText(automates[0].toString());
                    students3.setText(String.valueOf(automates[0].current_student));
                    if (status3.getText().equals("Payment")){
                        sum3.setText(String.valueOf(automates[0].getEarnings()));
                    }
                    break;
                case "4":
                    status4.setText(automates[0].status.toString());
                    products4.setText(automates[0].toString());
                    students4.setText(String.valueOf(automates[0].current_student));
                    if (status4.getText().equals("Payment")){
                        sum4.setText(String.valueOf(automates[0].getEarnings()));
                    }
                    break;
            }
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

    }
}