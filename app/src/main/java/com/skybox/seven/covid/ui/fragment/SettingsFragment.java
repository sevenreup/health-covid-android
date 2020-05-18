package com.skybox.seven.covid.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.SettingsController;
import com.skybox.seven.covid.ui.activities.AuthActivity;
import com.skybox.seven.covid.viewmodels.factories.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.Locale;

import static com.skybox.seven.covid.Covid.SUPPORTED_LOCALES;


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
        Spinner spinner = dialogView.findViewById(R.id.language_spinner);
        MaterialButton button = dialogView.findViewById(R.id.save_locale);
        ArrayAdapter<Locale> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, SUPPORTED_LOCALES);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        button.setOnClickListener(v -> viewModel.changeLanguage.setValue((Locale) spinner.getSelectedItem()));
        dialog.setContentView(dialogView);
    }
    @Override
    public void onContactsClick() {
        Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.allconacts);
    }

    @Override
    public void onNotificationClick() {
        ((TextView)dialogView.findViewById(R.id.btn_title)).setText(R.string.settings_notification);
        dialog.show();
    }

    @Override
    public void onLanguageClick() {
        ((TextView)dialogView.findViewById(R.id.btn_title)).setText(R.string.settings_language);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }

    @Override
    public void onPause() {
        super.onPause();
        dialog.dismiss();
    }
}
