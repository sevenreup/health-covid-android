package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.chip.ChipGroup;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.Myth;
import com.skybox.seven.covid.model.MythGraphicInfo;
import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.ui.adapters.TipsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MythsFragment extends Fragment {

    private ChipGroup currentGroup;


    public MythsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myths, container, false);

        currentGroup = v.findViewById(R.id.myth_chip_group);
        ViewPager2 viewPager2 = v.findViewById(R.id.viewPager);

        TipsAdapter tipsAdapter = new TipsAdapter(getChildFragmentManager(), getLifecycle(), getPages());
        viewPager2.setAdapter(tipsAdapter);

//
//
//        currentGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            switch (checkedId) {
//                case R.id.myth_chip:
//                    lastChecked = checkedId;
//                    currentChip = Myth.CurrentChip.myth;
//                    controller.setData(currentChip, mythList, mythGraphicInfoList);
//
//                    break;
//                case R.id.myth_info_chip:
//                    lastChecked = checkedId;
//                    currentChip = Myth.CurrentChip.mythgraphicinfo;
//                    controller.setData(currentChip, mythList, mythGraphicInfoList);
//                    break;
//                default:
//                    group.check(lastChecked);
//                    break;
//            }
//        });
//
//        currentGroup.check(R.id.myth_chip);
//
//        viewModel.mythList.observe(getViewLifecycleOwner(), list -> {
//            mythList = list;
//            controller.setData(currentChip, mythList, mythGraphicInfoList);
//        });
//        viewModel.mythGraphicInfoList.observe(getViewLifecycleOwner(), list -> mythGraphicInfoList = list);
//
//        viewModel.getMythList();
//        controller.setData(currentChip, mythList, mythGraphicInfoList);
        return v;
    }

    private List<TipsChips> getPages() {
        List<TipsChips> chips = new ArrayList<>();
        chips.add(TipsChips.myth);
        chips.add(TipsChips.mythgraphicinfo);
        return chips;
    }
}

