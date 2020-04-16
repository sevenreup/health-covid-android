package com.skybox.seven.covid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.HomeActivity;
import com.skybox.seven.covid.ui.UiTestActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    EditText userName;
    EditText userNumber;
    Button loginButton;
    TextView registerView;
    private static FragmentManager fragmentManager;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        userName = v.findViewById(R.id.userName);
        userNumber = v.findViewById(R.id.userNumber);
        loginButton = v.findViewById(R.id.loginButton);
        registerView = v.findViewById(R.id.registerView);

       loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameM = userName.getText().toString();
                String userNumberM = userNumber.getText().toString();
                Intent intent = new Intent();
                intent.setClass(getActivity(),HomeActivity.class);
                intent.putExtra(HomeActivity.NAME_MESSAGE,userNameM);
                intent.putExtra(HomeActivity.PHONE_MESSAGE, userNumberM);
                getActivity().startActivity(intent);
                startActivity(intent);
            }
    });

      registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    RegisterFragment registerFragment = new RegisterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainlayout, registerFragment);
                transaction.commit();
            }
        });

    return v;
    }

}