package com.skybox.seven.covid.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.viewmodels.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private TextInputLayout firstnameInput, lastnameInput, phonenumberInput, passwordInput;
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

        firstnameInput = v.findViewById(R.id.firstName);
        lastnameInput = v.findViewById(R.id.lastName);
        phonenumberInput = v.findViewById(R.id.contactNumber);
        passwordInput = v.findViewById(R.id.userPassword);

        v.findViewById(R.id.submitButton).setOnClickListener(v1 -> {
            viewModel.register(firstnameInput.getEditText().getText().toString(),
                    lastnameInput.getEditText().getText().toString(),
                    phonenumberInput.getEditText().getText().toString(),
                    passwordInput.getEditText().getText().toString());

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
