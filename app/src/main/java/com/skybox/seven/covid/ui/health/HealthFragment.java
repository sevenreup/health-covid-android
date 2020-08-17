package com.skybox.seven.covid.ui.health;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.adapters.TipsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment {
    List<TipsChips> chips = new ArrayList<>();
    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_myths, container, false);
        getPages();
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);

        ViewPager2 viewPager2 = v.findViewById(R.id.viewPager);
        TipsAdapter tipsAdapter = new TipsAdapter(getChildFragmentManager(), getLifecycle(), chips);
        viewPager2.setAdapter(tipsAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (chips.get(position)) {
                case advice:
                    tab.setText(R.string.health_advice);
                    break;
                case infographic:
                    tab.setText(R.string.health_info_graphic);
                    break;
            }
        }).attach();
        return v;
    }

    private void getPages() {
        chips.add(TipsChips.advice);
        chips.add(TipsChips.infographic);
    }
}
