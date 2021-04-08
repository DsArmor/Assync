package com.example.assync;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

public class VendingMachineFragment extends Fragment {

    public TextView machine;
    public TextView status;
    public TextView students;
    public TextView products;
    public TextView sum;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vending_machine1, container, false);

        machine = view.findViewById(R.id.machine);
        status = view.findViewById(R.id.status);
        students = view.findViewById(R.id.students);
        products = view.findViewById(R.id.products);
        sum = view.findViewById(R.id.sum);

        snackAdapter = new SnackAdapter();
        snacksField.setAdapter(snackAdapter);
        snacksField.setLayoutManager(new LinearLayoutManager(getContext()));
        queueAdapter = new QueueAdapter();
        queueField.setLayoutManager(new LinearLayoutManager(getContext()));
        queueField.setAdapter(queueAdapter);

        fullscreen = false;

        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!fullscreen) {
                            ((MainActivity)getActivity()).makeFullScreen(thisFragment);
                        }
                    }
                });

        updateView();

        return view;
    }

}
