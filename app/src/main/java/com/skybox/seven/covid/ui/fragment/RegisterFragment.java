package com.skybox.seven.covid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.HomeActivity;
import com.skybox.seven.covid.util.BaseModelFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private TextView firstname, lastname, phonenumber, gender;
    private MainViewModel viewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(MainViewModel.class);

        firstname = v.findViewById(R.id.firstName);
        lastname = v.findViewById(R.id.lastName);
        phonenumber = v.findViewById(R.id.contactNumber);
        gender = v.findViewById(R.id.contactgender);

        v.findViewById(R.id.submitButton).setOnClickListener(v1 -> {
            viewModel.register(firstname.getText().toString(), lastname.getText().toString(), phonenumber.getText().toString(), gender.getText().toString());

        });
        viewModel.isRegistered.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.mainlayout, new LoginFragment());
                transaction.commit();
            }
        });
    return v;
    }

}
