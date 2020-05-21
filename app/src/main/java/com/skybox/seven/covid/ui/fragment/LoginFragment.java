package com.skybox.seven.covid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.responses.ValidationErrors;
import com.skybox.seven.covid.ui.activities.HomeActivity;
import com.skybox.seven.covid.viewmodels.AuthViewModel;

import java.lang.ref.WeakReference;

import io.radar.sdk.Radar;

/**
 * Login Fragment {@link Fragment}.
 */
public class LoginFragment extends Fragment {
    private AuthViewModel viewModel;
    private TextInputLayout phoneTextField;
    private TextInputLayout passwordTextField;
    private TextView authErrors;

    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AuthViewModel.class);

        phoneTextField = v.findViewById(R.id.phone_number);
        passwordTextField = v.findViewById(R.id.userPassword);
        authErrors = v.findViewById(R.id.auth_errors);

        viewModel.credentials.observe(getViewLifecycleOwner(), loginResponse -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(),HomeActivity.class);
            intent.putExtra(HomeActivity.NAME_MESSAGE,loginResponse.getName());
            intent.putExtra(HomeActivity.PHONE_MESSAGE, loginResponse.getPhone());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Radar.setUserId(loginResponse.getId());
            getActivity().startActivity(intent);
            startActivity(intent);
            getActivity().finishAffinity();
        });

        viewModel.loading.observe(getViewLifecycleOwner(), loading -> {
            if (loading) {
                (v.findViewById(R.id.loading_progress)).setVisibility(View.VISIBLE);
            } else {
                (v.findViewById(R.id.loading_progress)).setVisibility(View.GONE);
            }
        });

        v.findViewById(R.id.loginButton).setOnClickListener(v1 -> {
            authErrors.setVisibility(View.GONE);
           String phone = phoneTextField.getEditText().getText().toString();
           String password = passwordTextField.getEditText().getText().toString();
           viewModel.login(phone, password);

       });

        v.findViewById(R.id.registerView).setOnClickListener(v12 -> {
              RegisterFragment registerFragment = new RegisterFragment();
              FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
              transaction.replace(R.id.mainlayout, registerFragment);
              transaction.commit();
      });

        viewModel.validationErrors.observe(getViewLifecycleOwner(), validationErrors -> {
            ValidationErrors.Errors errors = validationErrors.getErrors();
            if (errors.getPassword() != null)
                if (errors.getPassword().size() > 0) {
                    String password = errors.getPassword().get(0);
                    ((TextInputLayout)v.findViewById(R.id.userPassword)).getEditText().setError(password);
                }

            if (errors.getPhone() != null)
                if (errors.getPhone().size() > 0) {
                    String phone = errors.getPhone().get(0);
                    ((TextInputLayout)v.findViewById(R.id.phone_number)).getEditText().setError(phone);
                }
        });

        viewModel.authErrors.observe(getViewLifecycleOwner(), s -> {
            authErrors.setText(s);
            authErrors.setVisibility(View.VISIBLE);
        });

    return v;
    }

    /**
     * PhoneNumber formatter and validator
     * Todo: actually make it work and whatnot
     */
    public class PhoneFormatter implements TextWatcher {
        WeakReference<EditText> mobile;

        public PhoneFormatter(WeakReference<EditText> mobile) {
            this.mobile = mobile;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            StringBuilder digits = new StringBuilder();
            StringBuilder phone = new StringBuilder();
            char[] chars = s.toString().toCharArray();


            for (int x = 0; x < chars.length; x++) {
                if (Character.isDigit(chars[x])) {
                    digits.append(chars[x]);
                }
            }

            //Format for 6 digits and below
            if (digits.toString().length() >= 6) {
                String six = new String();
                six += digits.toString();
                phone.append(six);
                //Format for 7 digits
                if (digits.toString().length() >= 7) {
                    phone.setLength(0);
                    String countryCode = new String();
                    countryCode += digits.toString().substring(0, 3) + "-";
                    countryCode += digits.toString().substring(3,7);

                    phone.append(countryCode);
                    //Format for 8 and 9 digits
                    if (digits.toString().length() >= 8) {
                        phone.setLength(0);
                        String eight = new String();
                        eight += digits.toString();
                        phone.append(eight);
                        //Format for 10 digits
                        if (digits.toString().length() >= 10) {
                            phone.setLength(0);

                            String regionCode = new String();

                            regionCode += "(" + digits.toString().substring(0, 3) + ") ";
                            regionCode += digits.toString().substring(3, 6) + "-";

                            phone.append(regionCode);
                            //Format for 11 digits
                            if (digits.toString().length() >= 11) {
                                String code = new String();
                                phone.setLength(0);
                                code += digits.toString().substring(0, 1);
                                code += "(" + digits.toString().substring(1, 4) + ")- ";
                                code += digits.toString().substring(4, 7) + "-";
                                code += digits.toString().substring(7, 11);
                                phone.append(code);
                                //Format for 12 digits
                                if (digits.toString().length() >= 12) {
                                    String newCode = new String();
                                    phone.setLength(0);
                                    newCode += "+" + digits.toString().substring(0, 2);
                                    newCode += "(" + digits.toString().substring(2, 5) + ")";
                                    newCode += digits.toString().substring(5, 8) + "-";
                                    newCode += digits.toString().substring(8, 12);

                                    phone.append(newCode);
                                    //Format for 12 digits and more
                                    if (digits.toString().length() >= 13) {
                                        String noMoreThanTwelve = new String();
                                        phone.setLength(0);
                                        noMoreThanTwelve += digits.toString();
                                        phone.append(noMoreThanTwelve);
                                    }
                                }
                            } else {
                                phone.append(digits.toString().substring(6));
                            }
                        }
                    }
                }
//                mobile.removeTextChangedListener(this);
//                mobile.setText(phone.toString());
//                mobile.setSelection(mobile.getText().toString().length());
//                mobile.addTextChangedListener(this);
                mobile.get().removeTextChangedListener(this);
                mobile.get().setText(phone.toString());
                mobile.get().setSelection(phone.toString().length());
                mobile.get().addTextChangedListener(this);

            } else {
                return;
            }
        }
    }

}