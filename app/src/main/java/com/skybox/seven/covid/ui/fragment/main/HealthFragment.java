package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;
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
import com.skybox.seven.covid.ui.bottomsheets.AdviceBottomSheetFragment;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.AdviceViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment {
    private EpoxyRecyclerView recyclerView;
    private ChipGroup currentGroup;
    private int lastChecked;
    private List<Advice> adviceList = new ArrayList<>();
    private List<InfoGraphic> infoGraphics = new ArrayList<>();
    private AdviceViewModel adviceViewModel;
    private AdviceBottomSheetFragment adviceBottomSheetFragment;

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health, container, false);

        adviceViewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AdviceViewModel.class);

        recyclerView = v.findViewById(R.id.health_recycler);
        currentGroup = v.findViewById(R.id.chip_group);
        adviceBottomSheetFragment = new AdviceBottomSheetFragment();

        HealthController controller = new HealthController(getActivity(), new HealthController.HealthTipsCallback() {
            @Override
            public void onAdviceClick(Advice advice) {
                adviceViewModel.activeAdvice.setValue(advice);
                adviceBottomSheetFragment.show(getChildFragmentManager(), null);
            }

            @Override
            public void onInfoGraphicClick(String graphic) {
                adviceViewModel.activeInfoGraphic.setValue(graphic);
                adviceBottomSheetFragment.show(getChildFragmentManager(), null);
            }
        });
        recyclerView.addItemDecoration(new SpaceItemDecorator(50, true, false));
        recyclerView.setController(controller);

        currentGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.advice_chip:
                    lastChecked = checkedId;
                    adviceViewModel.activeChip.setValue(Advice.CurrentChip.advice);
                    controller.setData(adviceViewModel.activeChip.getValue(), adviceList, infoGraphics);

                    break;
                case R.id.info_chip:
                    lastChecked = checkedId;
                    adviceViewModel.activeChip.setValue(Advice.CurrentChip.infographic);
                    controller.setData(adviceViewModel.activeChip.getValue(), adviceList, infoGraphics);
                    break;
                default:
                    group.check(lastChecked);
                    break;
            }
        });

        currentGroup.check(R.id.advice_chip);

        adviceViewModel.adviceList.observe(getViewLifecycleOwner(), list -> {
            adviceList = list;
            controller.setData(adviceViewModel.activeChip.getValue(), adviceList, infoGraphics);
        });
        adviceViewModel.infoGraphicList.observe(getViewLifecycleOwner(), list -> infoGraphics = list);

        adviceViewModel.getAdviceList();
        controller.setData(adviceViewModel.activeChip.getValue(), adviceList, infoGraphics);
        return v;
    }
}
