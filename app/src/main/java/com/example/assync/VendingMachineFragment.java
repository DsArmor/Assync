package com.example.assync;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class VendingMachineFragment extends Fragment {

    TextView machine1;
    TextView status1;
    TextView sum1;
    TextView products1;
    TextView students1;
    Automate automate;

    VendingMachineFragment fragment = this;

    public static VendingMachineFragment newInstance(){
        VendingMachineFragment fragment = new VendingMachineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vending_machine1, container, false);
        machine1 = view.findViewById(R.id.machine1);
        status1 = view.findViewById(R.id.status1);
        sum1 = view.findViewById(R.id.sum1);
        products1 = view.findViewById(R.id.products1);
        students1 = view.findViewById(R.id.students1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).Full(fragment, automate);
            }
        });
        return view;
    }
}
