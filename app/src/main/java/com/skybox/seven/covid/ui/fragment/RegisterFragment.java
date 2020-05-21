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

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private TextInputLayout firstNameInput, lastNameInput, phoneNumberInput, passwordInput;
    private AuthViewModel viewModel;

    public RegisterFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AuthViewModel.class);

        firstNameInput = v.findViewById(R.id.firstName);
        lastNameInput = v.findViewById(R.id.lastName);
        phoneNumberInput = v.findViewById(R.id.contactNumber);
        passwordInput = v.findViewById(R.id.userPassword);

        v.findViewById(R.id.submitButton).setOnClickListener(v1 -> {
            viewModel.register(firstNameInput.getEditText().getText().toString(),
                    lastNameInput.getEditText().getText().toString(),
                    phoneNumberInput.getEditText().getText().toString(),
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
        viewModel.validationErrors.observe(getViewLifecycleOwner(), validationErrors -> {
            ValidationErrors.Errors errors = validationErrors.getErrors();
            if (errors.getFirstName() != null)
                if (errors.getFirstName().size() > 0) {
                    String fName = errors.getFirstName().get(0);
                    ((TextInputLayout)v.findViewById(R.id.firstName)).getEditText().setError(fName);
                }
            if (errors.getLastName() != null)
                if (errors.getLastName().size() > 0) {
                    String lName = errors.getLastName().get(0);
                    ((TextInputLayout)v.findViewById(R.id.lastName)).getEditText().setError(lName);
                }

            if (errors.getPassword() != null)
                if (errors.getPassword().size() > 0) {
                    String password = errors.getPassword().get(0);
                    ((TextInputLayout)v.findViewById(R.id.userPassword)).getEditText().setError(password);
                }

            if (errors.getPhone() != null)
                if (errors.getPhone().size() > 0) {
                    String phone = errors.getPhone().get(0);
                    ((TextInputLayout)v.findViewById(R.id.contactNumber)).getEditText().setError(phone);
                }

        });
    return v;
    }

}
