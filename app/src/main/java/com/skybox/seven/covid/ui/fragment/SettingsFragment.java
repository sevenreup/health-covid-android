package com.skybox.seven.covid.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.SettingsController;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements SettingsController.SettingsCallback {
    private EpoxyRecyclerView recyclerView;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView = v.findViewById(R.id.settings_recycler);

        SettingsController controller = new SettingsController(this);
        recyclerView.setController(controller);
        controller.requestModelBuild();
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

    }

    @Override
    public void onHelpClick() {

    }
}
