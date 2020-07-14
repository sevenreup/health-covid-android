package com.skybox.seven.covid.ui.common;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.settings.SettingsController;
import com.skybox.seven.covid.ui.auth.AuthActivity;
import com.skybox.seven.covid.util.Constants;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.ui.main.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements SettingsController.SettingsCallback {
    private EpoxyRecyclerView recyclerView;
    private MainViewModel viewModel;
    BottomSheetDialog dialog;
    View dialogView;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        viewModel = new ViewModelProvider(getActivity(), InjectorUtil.provideHomeViewModelFactory(getContext())).get(MainViewModel.class);

        recyclerView = v.findViewById(R.id.settings_recycler);

        SettingsController controller = new SettingsController(this);
        recyclerView.setController(controller);

        controller.setData(viewModel.isLoggedIn(), viewModel.showLoginNotification.getValue());
        viewModel.showLoginNotification.observe(getViewLifecycleOwner(), aBoolean -> controller.setData(viewModel.isLoggedIn(), aBoolean));

        return v;
    }

    @Override
    public void onContactsClick() {
        Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.allconacts);
    }

    @Override
    public void onNotificationClick() {
    }

    @Override
    public void onLanguageClick() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(R.string.settings_language);
        String[] allLanguages = {"English", "Chichewa"};
        alertDialog.setSingleChoiceItems(allLanguages, (viewModel.getLanguage() - 1), (dialog, which) -> {
            switch (which) {
                case 0:
                    viewModel.setLanguage(Constants.ENGLISH);
                    break;
                case 1:
                    viewModel.setLanguage(Constants.CHICHEWA);
                    break;
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onHelpClick() {

    }

    @Override
    public void onLogoutClick() {
        viewModel.logout();
    }

    @Override
    public void onLoginClick() {
        Intent intent = new Intent(getContext(), AuthActivity.class);
        intent.putExtra("register", false);
        startActivity(intent);
    }

    @Override
    public void onRegisterClick() {
        Intent intent = new Intent(getContext(), AuthActivity.class);
        intent.putExtra("register", true);
        startActivity(intent);
    }

    @Override
    public void onLogNotClose() {
        viewModel.showLoginNotification.setValue(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
