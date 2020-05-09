package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.HealthController;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.ui.adapters.TipsAdapter;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.TipsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment {

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health, container, false);

        TipsViewModel viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(TipsViewModel.class);

        ViewPager2 viewPager2 = v.findViewById(R.id.viewPager);
        TipsAdapter tipsAdapter = new TipsAdapter(getChildFragmentManager(), getLifecycle(), getPages());
        viewPager2.setAdapter(tipsAdapter);
        return v;
    }

    private List<TipsChips> getPages() {
        List<TipsChips> chips = new ArrayList<>();
        chips.add(TipsChips.advice);
        chips.add(TipsChips.infographic);
        return chips;
    }
}
