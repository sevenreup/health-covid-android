package com.skybox.seven.covid.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.skybox.seven.covid.ui.fragment.main.ContactRequestFragment;
import com.skybox.seven.covid.ui.fragment.main.ContactTraceFragment;

import java.util.ArrayList;
import java.util.List;

public class ContactsPageAdapter extends FragmentStatePagerAdapter{
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String>mFragmentTitleList = new ArrayList<>();

    public ContactsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
        }

    public void  addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
