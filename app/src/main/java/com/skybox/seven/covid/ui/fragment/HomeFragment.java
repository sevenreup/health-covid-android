package com.skybox.seven.covid.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.ContactActivity;
import com.skybox.seven.covid.ui.HomeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    TextView userName;
    TextView userNumber;
    String user, number;
    CardView contactCard;
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
        contactCard = v.findViewById(R.id.contactCard);
        Bundle bundle = getArguments();
        user = bundle.getString("username");
        number = bundle.getString("number");
        userNumber.setText(number);
        userName.setText(user);

        contactCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), ContactActivity.class);

                getActivity().startActivity(intent);
                startActivity(intent);
            }
        });


        return v;


    }

}
