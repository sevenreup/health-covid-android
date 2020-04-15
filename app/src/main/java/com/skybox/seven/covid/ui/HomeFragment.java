package com.skybox.seven.covid.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.skybox.seven.covid.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    TextView userName;
    TextView userNumber;
    String user, number;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        userName = v.findViewById(R.id.userName);
        userNumber = v.findViewById(R.id.userNumber);
        Bundle bundle = getArguments();
        user = bundle.getString("username");
        number = bundle.getString("number");
        userNumber.setText(number);
        userName.setText(user);
        return v;
    }

}
