package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.google.android.material.chip.ChipGroup;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.HealthController;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.InfoGraphic;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment {
    private EpoxyRecyclerView recyclerView;
    private ChipGroup currentGroup;
    private int lastChecked;
    private Advice.CurrentChip currentChip = Advice.CurrentChip.advice;
    private List<Advice> adviceList = new ArrayList<>();
    private List<InfoGraphic> infoGraphics = new ArrayList<>();
    MainViewModel viewModel;

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health, container, false);
        viewModel = new ViewModelProvider(getViewModelStore(), new CovidFactory(getActivity().getApplication())).get(MainViewModel.class);

        recyclerView = v.findViewById(R.id.health_recycler);
        currentGroup = v.findViewById(R.id.chip_group);

        HealthController controller = new HealthController(getActivity());
        recyclerView.addItemDecoration(new SpaceItemDecorator(50, true, false));
        recyclerView.setController(controller);

        currentGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.advice_chip:
                    lastChecked = checkedId;
                    currentChip = Advice.CurrentChip.advice;
                    controller.setData(currentChip, adviceList, infoGraphics);

                    break;
                case R.id.info_chip:
                    lastChecked = checkedId;
                    currentChip = Advice.CurrentChip.infographic;
                    controller.setData(currentChip, adviceList, infoGraphics);
                    break;
                default:
                    group.check(lastChecked);
                    break;
            }
        });

        currentGroup.check(R.id.advice_chip);

        viewModel.adviceList.observe(getViewLifecycleOwner(), list -> {
            adviceList = list;
            controller.setData(currentChip, adviceList, infoGraphics);
        });
        viewModel.infoGraphicList.observe(getViewLifecycleOwner(), list -> infoGraphics = list);

        viewModel.getAdviceList();
        controller.setData(currentChip, adviceList, infoGraphics);
        return v;
    }
}
