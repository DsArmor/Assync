package com.example.assync;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Fragment_automate3 extends Fragment {
    @Override
    // Переопределяем метод onCreateView
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // менеджер компоновки, который позволяет
        // получать доступ к layout с наших ресурсов
        View view = inflater
                .inflate(R.layout.vending_machine3, container, false);


        return view;
    }
}
