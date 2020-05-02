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
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.HomeActivity;
import com.skybox.seven.covid.viewmodels.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private MainViewModel viewModel;
    private EditText userName;
    private EditText userNumber;
    private Button loginButton;
    private TextView registerView;
    private static FragmentManager fragmentManager;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(MainViewModel.class);

        userName = v.findViewById(R.id.userName);
        userNumber = v.findViewById(R.id.userNumber);
        loginButton = v.findViewById(R.id.loginButton);
        registerView = v.findViewById(R.id.registerView);

        viewModel.credentials.observe(getActivity(), loginResponse -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(),HomeActivity.class);
            intent.putExtra(HomeActivity.NAME_MESSAGE,loginResponse.getName());
            intent.putExtra(HomeActivity.PHONE_MESSAGE, loginResponse.getPhone());

            getActivity().startActivity(intent);
            startActivity(intent);
        });

       loginButton.setOnClickListener(v1 -> {
           String userNameM = userName.getText().toString();
           String userNumberM = userNumber.getText().toString();
           viewModel.login(userNameM, userNumberM);

       });

      registerView.setOnClickListener(v12 -> {
              RegisterFragment registerFragment = new RegisterFragment();
              FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
              transaction.replace(R.id.mainlayout, registerFragment);
              transaction.commit();
      });

    return v;
    }

}