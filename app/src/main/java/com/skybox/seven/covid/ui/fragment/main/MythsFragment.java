package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.google.android.material.chip.ChipGroup;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.MythController;
import com.skybox.seven.covid.model.Myth;
import com.skybox.seven.covid.model.MythGraphicInfo;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MythsFragment extends Fragment {
    private EpoxyRecyclerView mythRecycler;
    private ChipGroup currentGroup;
    private int lastChecked;
    private Myth.CurrentChip currentChip = Myth.CurrentChip.myth;
    private List<Myth> mythList = new ArrayList<>();
    private List<MythGraphicInfo> mythGraphicInfoList = new ArrayList<>();
    MainViewModel viewModel;

    public MythsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myths, container, false);
        viewModel = new ViewModelProvider(getViewModelStore(), new CovidFactory(getActivity().getApplication())).get(MainViewModel.class);

        mythRecycler = v.findViewById(R.id.mythRecycler);
        currentGroup = v.findViewById(R.id.myth_chip_group);

        MythController controller = new MythController(getActivity());
        mythRecycler.addItemDecoration(new SpaceItemDecorator(50, true, false));
        mythRecycler.setController(controller);

        currentGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.myth_chip:
                    lastChecked = checkedId;
                    currentChip = Myth.CurrentChip.myth;
                    controller.setData(currentChip, mythList, mythGraphicInfoList);

                    break;
                case R.id.myth_info_chip:
                    lastChecked = checkedId;
                    currentChip = Myth.CurrentChip.mythgraphicinfo;
                    controller.setData(currentChip, mythList, mythGraphicInfoList);
                    break;
                default:
                    group.check(lastChecked);
                    break;
            }
        });

        currentGroup.check(R.id.myth_chip);

        viewModel.mythList.observe(getViewLifecycleOwner(), list -> {
            mythList = list;
            controller.setData(currentChip, mythList, mythGraphicInfoList);
        });
        viewModel.mythGraphicInfoList.observe(getViewLifecycleOwner(), list -> mythGraphicInfoList = list);

        viewModel.getMythList();
        controller.setData(currentChip, mythList, mythGraphicInfoList);
        return v;  }


    }

