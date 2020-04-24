package com.skybox.seven.covid.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.SettingsController;
import com.skybox.seven.covid.ui.AuthActivity;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;


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

        viewModel = new ViewModelProvider(getActivity(), new CovidFactory(getActivity().getApplication())).get(MainViewModel.class);

        recyclerView = v.findViewById(R.id.settings_recycler);
        setupDialog();

        SettingsController controller = new SettingsController(this);
        recyclerView.setController(controller);

        controller.setData(viewModel.isLoggedIn(), viewModel.showLoginNotification.getValue());
        viewModel.showLoginNotification.observe(getViewLifecycleOwner(), aBoolean -> controller.setData(viewModel.isLoggedIn(), aBoolean));

        return v;
    }

    private void setupDialog() {

        dialog = new BottomSheetDialog(getContext());
        dialogView = dialog.getLayoutInflater().inflate(R.layout.bottom_settings_sheet, null);
        dialog.setContentView(dialogView);

    }
    @Override
    public void onContactsClick() {
        Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.allconacts);
    }

    @Override
    public void onNotificationClick() {
        ((TextView)dialogView.findViewById(R.id.btn_title)).setText("Change notifications");
        dialog.show();
    }

    @Override
    public void onLanguageClick() {
        ((TextView)dialogView.findViewById(R.id.btn_title)).setText("Set the Current Language");
        dialogView.findViewById(R.id.btn_lan_hd).setVisibility(View.VISIBLE);
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
}
