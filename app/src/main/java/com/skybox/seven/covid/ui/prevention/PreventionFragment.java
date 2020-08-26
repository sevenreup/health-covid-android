package com.skybox.seven.covid.ui.prevention;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.prevention.PreventionController;
import com.skybox.seven.covid.model.PreventionModel;
import com.skybox.seven.covid.ui.advice.AdviceViewModel;
import com.skybox.seven.covid.util.SpaceItemDecorator;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PreventionFragment extends Fragment {

    private PreventionController controller;
    private AdviceViewModel viewModel;


    public PreventionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prevention, container, false);
        EpoxyRecyclerView recyclerView = v.findViewById(R.id.PreventionRecyclerView);

        viewModel = new ViewModelProvider(getActivity()).get(AdviceViewModel.class);
        controller = new PreventionController();

        recyclerView.addItemDecoration(new SpaceItemDecorator(20, true, false));
        recyclerView.setController(controller);

        viewModel.adviceList.observe(getViewLifecycleOwner(), preventionModel -> controller.setData(false,preventionModel));

        viewModel.getAdviceList();
        return v;
    }
}