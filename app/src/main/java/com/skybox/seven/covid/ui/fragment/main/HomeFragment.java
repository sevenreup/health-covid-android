package com.skybox.seven.covid.ui.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.responses.LoginResponse;
import com.skybox.seven.covid.ui.ContactActivity;
import com.skybox.seven.covid.ui.HomeActivity;
import com.skybox.seven.covid.util.BaseModelFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private TextView userName;
    private TextView userNumber;
    private MainViewModel viewModel;
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
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(MainViewModel.class);

        viewModel.credentials.observe(getActivity(), loginResponse -> {
            userNumber.setText(loginResponse.getPhone());
            userName.setText(loginResponse.getName());
        });

        return v;
    }

}
