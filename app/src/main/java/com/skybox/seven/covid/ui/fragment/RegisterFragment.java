package com.skybox.seven.covid.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.responses.ValidationErrors;
import com.skybox.seven.covid.viewmodels.AuthViewModel;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private TextInputLayout firstnameInput, lastnameInput, phonenumberInput, passwordInput;
    private AuthViewModel viewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AuthViewModel.class);

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

        viewModel.loading.observe(getViewLifecycleOwner(), loading -> {
            if (loading)
                v.findViewById(R.id.loading_progress).setVisibility(View.VISIBLE);
            else
                v.findViewById(R.id.loading_progress).setVisibility(View.GONE);
        });
        viewModel.isRegistered.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.mainlayout, new LoginFragment());
                transaction.commit();
            }
        });
        viewModel.validationErrors.observe(getViewLifecycleOwner(), new Observer<ValidationErrors>() {
            @Override
            public void onChanged(ValidationErrors validationErrors) {
                ValidationErrors.Errors errors = validationErrors.getErrors();
                if (errors.getFirstName().size() > 0) {
                    String fName = errors.getFirstName().get(0);
                    ((TextInputLayout)v.findViewById(R.id.firstName)).getEditText().setError(fName);
                }

                if (errors.getLastName().size() > 0) {
                    String lName = errors.getLastName().get(0);
                    ((TextInputLayout)v.findViewById(R.id.lastName)).getEditText().setError(lName);
                }
                if (errors.getPassword().size() > 0) {
                    String password = errors.getPassword().get(0);
                    ((TextInputLayout)v.findViewById(R.id.userPassword)).getEditText().setError(password);
                }

                if (errors.getPhone().size() > 0) {
                    String phone = errors.getPhone().get(0);
                    ((TextInputLayout)v.findViewById(R.id.contactNumber)).getEditText().setError(phone);
                }

            }
        });
    return v;
    }

}
