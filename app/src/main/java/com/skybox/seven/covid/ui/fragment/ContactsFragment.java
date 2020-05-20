package com.skybox.seven.covid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.adapters.ContactsPageAdapter;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.viewmodels.ContactsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
    ContactsViewModel viewModel;

    public ContactsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contacts_tab, container, false);

        TabLayout tabLayout = v.findViewById(R.id.contactTab);
        ViewPager viewPager = v.findViewById(R.id.viewPager);
        TabItem contacts = v.findViewById(R.id.allContacts);
        TabItem requests = v.findViewById(R.id.requests);
        FloatingActionButton addContButton = v.findViewById(R.id.addContButton);
        MaterialCardView cardView = v.findViewById(R.id.error_holder);

        viewModel = new ViewModelProvider(getActivity(), InjectorUtil.provideContactsViewModelFactory(getContext())).get(ContactsViewModel.class);
        viewModel.networkLoading.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    cardView.setVisibility(View.VISIBLE);
                } else {
                    cardView.setVisibility(View.GONE);
                }
            }
        });

        addContButton.setOnClickListener(v1 -> Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.createContacts));

        ContactsPageAdapter pagerAdapter = new ContactsPageAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new ContactTraceFragment(), getString(R.string.contacts_title));
        pagerAdapter.addFragment(new ContactRequestFragment(), getString(R.string.contacts_request_title));

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }
}
