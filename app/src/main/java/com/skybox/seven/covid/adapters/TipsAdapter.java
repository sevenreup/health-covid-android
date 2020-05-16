package com.skybox.seven.covid.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.ui.fragment.main.TipsPagerFragment;

import java.util.List;

public class TipsAdapter extends FragmentStateAdapter {
    List<TipsChips> chipList;

    public TipsAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<TipsChips> chipList) {
        super(fragmentManager, lifecycle);
        this.chipList = chipList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new TipsPagerFragment(chipList.get(position));
    }

    @Override
    public int getItemCount() {
        return chipList.size();
    }
}
