package com.skybox.seven.covid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.adapters.ContactsPageAdapter;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.viewmodels.ContactsViewModel;

/**
 * A View that holds all the contact pager {@link Fragment}.
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
        ViewPager2 viewPager = v.findViewById(R.id.viewPager);
        FloatingActionButton addContButton = v.findViewById(R.id.addContButton);
        MaterialCardView cardView = v.findViewById(R.id.error_holder);

        viewModel = new ViewModelProvider(getActivity(), InjectorUtil.provideContactsViewModelFactory(getContext())).get(ContactsViewModel.class);
        viewModel.networkLoading.observe(getActivity(), aBoolean -> {
            if (aBoolean) {
                cardView.setVisibility(View.VISIBLE);
            } else {
                cardView.setVisibility(View.GONE);
            }
        });

        addContButton.setOnClickListener(v1 -> Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.createContacts));

        ContactsPageAdapter pagerAdapter = new ContactsPageAdapter(getChildFragmentManager(), getLifecycle());

        viewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.contacts_title);
                    break;
                case 1:
                    tab.setText(R.string.contacts_request_title);
                    break;
            }
        }).attach();

        return v;
    }
}
