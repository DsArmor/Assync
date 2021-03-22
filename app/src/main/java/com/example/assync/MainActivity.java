package com.example.assync;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity {

    //Автомат со снеками, в нем есть разные варианты снеков\
    //Пополнение снеков через автомат
    //
    //Есть покупатель и 4 автомата, есть админ(следит), у него есть планшет, в котором выводится информация по 4 автоматам
    //20 студентов идут пожрать
    //рандомное распределение
    //Разбитый экран на 4 части, в каждом блоке пишется название автомата
    //TODO:
    //статус атомата(простаивание, прием, оплата, выдача)
    //номер студента
    //(у всех одинаковый перечень товаров, но разные остатки)
    //внизу показываем перечень всех товаров, которые может купить чел
    //TODO: recyclerView(view holder, adapter) для товаров
    //левый нижний - блок очереди, правый нижний - сумма заказа
    //Todo:(что должно быть)
    //Фабрики
    //Одиночка
    //Все 4 автомата работают одновременно
    //если вызовете экзикьют, то будет хуйня
    //execute assync task together
    //кто у вас стек: жэ



    //рандомно сформировать очереди к автоматам
    //Класс студентов
    //Фабрику еды
    //Фабрику напитков
    //Класс автомата с конструктором рандомного заполнения товарами
    //Поток из автоматов
    //одновременная обработка 4 автоматов
    //каждый студент своим действием слипает автомат
    String[] list_of_products = {"CocaCola", "Pepsi", "Popcorn", "Shawarma"};
    String[] list_of_automates = {"1", "2", "3", "4"};

    Automate automate1 = new Automate();
    Automate automate2 = new Automate();
    Automate automate3 = new Automate();
    Automate automate4 = new Automate();
    Student[] students = new Student[20];
    void putStudents(Student[] students){
        for (Student student: students){

        }
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
        TextView machine1 = (TextView)findViewById(R.id.machine1);
        machine1.setText("First automate");
        TextView status1 = (TextView)findViewById(R.id.status1);
        TextView sum1 = (TextView)findViewById(R.id.sum_1);
        TextView products1 = (TextView)findViewById(R.id.products_1);
        TextView students1 = (TextView)findViewById(R.id.students_1);

        TextView machine2 = (TextView)findViewById(R.id.machine2);
        machine2.setText("Second automate");
        TextView status2 = (TextView)findViewById(R.id.status2);
        TextView sum2 = (TextView)findViewById(R.id.sum_2);
        TextView products2 = (TextView)findViewById(R.id.products_2);
        TextView students2 = (TextView)findViewById(R.id.students_2);


        TextView machine3 = (TextView)findViewById(R.id.machine3);
        machine3.setText("Third automate");
        TextView status3 = (TextView)findViewById(R.id.status3);
        TextView sum3 = (TextView)findViewById(R.id.sum_3);
        TextView products3 = (TextView)findViewById(R.id.products_3);
        TextView students3 = (TextView)findViewById(R.id.students_3);


        TextView machine4 = (TextView)findViewById(R.id.machine4);
        machine4.setText("Fourth automate");
        TextView status4 = (TextView)findViewById(R.id.status4);
        TextView sum4 = (TextView)findViewById(R.id.sum_4);
        TextView products4 = (TextView)findViewById(R.id.products_4);
        TextView students4 = (TextView)findViewById(R.id.students_4);

        EditText count_of_products = findViewById(R.id.count_of_products);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        Button add = (Button)findViewById(R.id.add_products);
        Button start = (Button)findViewById(R.id.start_game);

        //обработка spinners для продуктов и автоматов
        ArrayAdapter<String> adapter_of_automates= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_of_automates);
        adapter_of_automates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter_of_products = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_of_products);
        adapter_of_products.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner_of_automates = (Spinner) findViewById(R.id.spinner_of_automates);
        spinner_of_automates.setAdapter(adapter_of_automates);

        Spinner spinner_of_products = (Spinner) findViewById(R.id.spinner_of_products);
        spinner_of_products.setAdapter(adapter_of_products);





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



    }
}