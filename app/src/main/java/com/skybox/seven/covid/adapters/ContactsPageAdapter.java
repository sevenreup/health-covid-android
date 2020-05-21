package com.skybox.seven.covid.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.skybox.seven.covid.ui.fragment.ContactRequestFragment;
import com.skybox.seven.covid.ui.fragment.ContactTraceFragment;

public class ContactsPageAdapter extends FragmentStateAdapter {

    public ContactsPageAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ContactTraceFragment();
            case 1:
                return new ContactRequestFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
