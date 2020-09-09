package com.skybox.seven.covid.ui.mythbusters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.mythbuster.MythBusterController;
import com.skybox.seven.covid.util.SpaceItemDecorator;

public class MythBustersFragment extends Fragment {

    private MythBusterController controller;
    private MythViewModel viewModel;

    public MythBustersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_myth_busters, container, false);
        EpoxyRecyclerView recyclerView = v.findViewById(R.id.MythRecyclerView);

        viewModel = new ViewModelProvider(getActivity()).get(MythViewModel.class);
        controller = new MythBusterController();

        recyclerView.addItemDecoration(new SpaceItemDecorator(20, true, false));
        recyclerView.setController(controller);

        viewModel.mythsList.observe(getViewLifecycleOwner(), mythbusterModel -> controller.setData(false,mythbusterModel));
        viewModel.getAllMyths();

        return v;
    }
}