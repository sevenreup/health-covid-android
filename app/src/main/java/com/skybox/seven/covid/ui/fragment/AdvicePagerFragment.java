package com.skybox.seven.covid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.epoxy.HealthController;
import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.AdviceViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvicePagerFragment extends Fragment {
    private EpoxyRecyclerView recyclerView;
    private TipsChips currentChip;

    private ImageViewerFragment imageViewerFragment;

    public AdvicePagerFragment() {
        currentChip = TipsChips.myth;
    }

    public AdvicePagerFragment(TipsChips currentChip) {
        this.currentChip = currentChip;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tips_pager, container, false);

        AdviceViewModel viewModel = new ViewModelProvider(getViewModelStore(), InjectorUtil.provideAdviceViewModelFactory(getContext())).get(AdviceViewModel.class);

        imageViewerFragment = new ImageViewerFragment(viewModel.activeInfoGraphic);

        recyclerView = v.findViewById(R.id.tips_recycler);
        recyclerView.addItemDecoration(new SpaceItemDecorator(50, true, false));

        if (currentChip == TipsChips.infographic) {
            viewModel.getInfoGraphicList();
            HealthController healthController = new HealthController(getActivity(), new HealthController.HealthTipsCallback() {
                @Override
                public void onAdviceClick(Advice advice) {
                    shareAdvice(advice);
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
                    shareAdvice(advice);
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

    private void shareAdvice(Advice advice) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, buildSharableString(advice));
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private String buildSharableString(Advice advice) {
        return getString(R.string.advice_title_bt_health_tip) +
                advice.getTitle() +
                getString(R.string.advice_title_bt_health_why) +
                advice.getAdvice();
    }

}
