package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.HealthController;
import com.skybox.seven.covid.epoxy.MythController;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.Myth;
import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.ui.bottomsheets.AdviceBottomSheetFragment;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;
import com.skybox.seven.covid.viewmodels.TipsViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipsPagerFragment extends Fragment {
    private EpoxyRecyclerView recyclerView;
    private TipsChips currentChip;

    private AdviceBottomSheetFragment adviceBottomSheetFragment;
    private ImageViewerFragment imageViewerFragment;

    public TipsPagerFragment() {
        currentChip = TipsChips.myth;
    }

    public TipsPagerFragment(TipsChips currentChip) {
        this.currentChip = currentChip;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tips_pager, container, false);

        TipsViewModel viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(TipsViewModel.class);


        adviceBottomSheetFragment = new AdviceBottomSheetFragment();
        imageViewerFragment = new ImageViewerFragment(viewModel.activeInfoGraphic);

        recyclerView = v.findViewById(R.id.tips_recycler);
        recyclerView.addItemDecoration(new SpaceItemDecorator(50, true, false));


        if (currentChip == TipsChips.myth) {
            viewModel.getMythList();
            MythController controller = new MythController(getActivity());
            recyclerView.setController(controller);
            controller.setData(currentChip, viewModel.mythList.getValue(), new ArrayList<>());
        } else if (currentChip == TipsChips.mythgraphicinfo) {
            viewModel.getMythGraphicList();
            MythController controller;
            controller = new MythController(getActivity());
            recyclerView.setController(controller);
            controller.setData(currentChip, new ArrayList<>(), viewModel.mythGraphicInfoList.getValue());
        } else if (currentChip == TipsChips.infographic) {
            viewModel.getInfoGraphicList();
            HealthController healthController = new HealthController(getActivity(), new HealthController.HealthTipsCallback() {
                @Override
                public void onAdviceClick(Advice advice) {
                    viewModel.activeAdvice.setValue(advice);
                    adviceBottomSheetFragment.show(getChildFragmentManager(), null);
                }

                @Override
                public void onInfoGraphicClick(String graphic) {
                    viewModel.activeInfoGraphic.setValue(graphic);
                    imageViewerFragment.show(getChildFragmentManager(), ImageViewerFragment.TAG);
                }
            });
            recyclerView.setController(healthController);
            healthController.setData(currentChip, null,viewModel.infoGraphicList.getValue());
        } else if (currentChip == TipsChips.advice) {
            viewModel.getAdviceList();
            HealthController healthController = new HealthController(getActivity(), new HealthController.HealthTipsCallback() {
                @Override
                public void onAdviceClick(Advice advice) {
                    viewModel.activeAdvice.setValue(advice);
                    adviceBottomSheetFragment.show(getChildFragmentManager(), null);
                }

                @Override
                public void onInfoGraphicClick(String graphic) {
                    viewModel.activeInfoGraphic.setValue(graphic);
                    imageViewerFragment.show(getChildFragmentManager(), ImageViewerFragment.TAG);
                }
            });
            recyclerView.setController(healthController);
            healthController.setData(currentChip, viewModel.adviceList.getValue(),null);
        }
        return v;
    }

}
