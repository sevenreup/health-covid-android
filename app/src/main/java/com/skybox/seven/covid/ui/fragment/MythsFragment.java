package com.skybox.seven.covid.ui.fragment;

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
public class MythsFragment extends Fragment {
    List<TipsChips> chips = new ArrayList<>();

    public MythsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myths, container, false);
        getPages();
        ViewPager2 viewPager2 = v.findViewById(R.id.viewPager);
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);

        TipsAdapter tipsAdapter = new TipsAdapter(getChildFragmentManager(), getLifecycle(), chips);
        viewPager2.setAdapter(tipsAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (chips.get(position)) {
                case myth:
                    tab.setText(R.string.myth_advice);
                    break;
                case mythgraphicinfo:
                    tab.setText(R.string.myth_info_graphic);
                    break;
            }
        }).attach();
        return v;
    }

    private void getPages() {
        chips.add(TipsChips.myth);
        chips.add(TipsChips.mythgraphicinfo);
    }
}

