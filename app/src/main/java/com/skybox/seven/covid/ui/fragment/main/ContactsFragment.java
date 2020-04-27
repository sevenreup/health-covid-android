package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.adapters.ContactsPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    ContactsPageAdapter pagerAdapter;
    TabItem contacts;
    TabItem requests;
    Button addContButton;

    public ContactsFragment() {
        // Required empty public constructor
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

        addContButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.createContacts);
            }
        });

        pagerAdapter = new ContactsPageAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new ContactTraceFragment(), "Contacts");
        pagerAdapter.addFragment(new ContactRequestFragment(), "Contact Requests");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

     //   pagerAdapter = new ContactsPageAdapter(getSupportFragment(), tableLayout.getTabCount());
       // viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));


        return v;
    }
}
