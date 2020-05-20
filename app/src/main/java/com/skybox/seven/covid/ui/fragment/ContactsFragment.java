package com.skybox.seven.covid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.adapters.ContactsPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ContactsPageAdapter pagerAdapter;
    private TabItem contacts;
    private TabItem requests;
    private FloatingActionButton addContButton;

    public ContactsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contacts_tab, container, false);
        tabLayout = v.findViewById(R.id.contactTab);
        viewPager = v.findViewById(R.id.viewPager);
        contacts = v.findViewById(R.id.allContacts);
        requests = v.findViewById(R.id.requests);
        addContButton = v.findViewById(R.id.addContButton);

        addContButton.setOnClickListener(v1 -> Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.createContacts));

        pagerAdapter = new ContactsPageAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new ContactTraceFragment(), getString(R.string.contacts_title));
        pagerAdapter.addFragment(new ContactRequestFragment(), getString(R.string.contacts_request_title));

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }
}
