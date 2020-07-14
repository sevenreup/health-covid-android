package com.skybox.seven.covid.ui.myth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.epoxy.myth.MythController;
import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.ui.common.ImageViewerFragment;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.util.SpaceItemDecorator;

import java.util.ArrayList;

public class MythTipsPagerFragment extends Fragment {
    private MythViewModel viewModel;
    private TipsChips tipsChips;
    private EpoxyRecyclerView recyclerView;
    private ImageViewerFragment imageViewerFragment;


    public MythTipsPagerFragment() {
        this.tipsChips = TipsChips.myth;
    }

    public MythTipsPagerFragment(TipsChips tipsChips) {
        this.tipsChips = tipsChips;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tips_pager, container, false);
        viewModel = new ViewModelProvider(getViewModelStore(), InjectorUtil.provideMythViewModelFactory(getContext())).get(MythViewModel.class);
        imageViewerFragment = new ImageViewerFragment(viewModel.activeInfoGraphic);

        recyclerView = v.findViewById(R.id.tips_recycler);
        recyclerView.addItemDecoration(new SpaceItemDecorator(50, true, false));

        setUpController();
        return v;
    }

    private void setUpController() {
        if (tipsChips == TipsChips.myth) {
            MythController controller = new MythController(getContext(), new MythController.MythCallback() {
                @Override
                public void onMythClick(Myth myth) {

                }

                @Override
                public void onInfoGraphicClick(String image) {

                }
            });
            recyclerView.setController(controller);
            viewModel.mythsList.observe(getViewLifecycleOwner(), myths -> {
                Log.e("TAG", myths.toString());
                controller.setData(tipsChips,myths, new ArrayList<>());
            });
            viewModel.getAllMyths();
        } else {
            MythController controller = new MythController(getContext(), new MythController.MythCallback() {
                @Override
                public void onMythClick(Myth myth) {

                }

                @Override
                public void onInfoGraphicClick(String image) {
                    viewModel.activeInfoGraphic.setValue(image);
                    imageViewerFragment.show(getChildFragmentManager(), ImageViewerFragment.TAG);
                }
            });
            recyclerView.setController(controller);
            viewModel.infoGraphics.observe(getViewLifecycleOwner(), myths -> {
                Log.e("TAG", myths.toString());
                controller.setData(tipsChips,new ArrayList<>(), myths);
            });
            viewModel.getAllInfoGraphics();
        }
    }

    private void shareAdvice(Myth myth) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, buildSharableString(myth));
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private String buildSharableString(Myth myth) {
        return getString(R.string.advice_title_bt_health_tip) +
                myth.getTitle() +
                getString(R.string.advice_title_bt_health_why) +
                myth.getMyth();
    }
}
