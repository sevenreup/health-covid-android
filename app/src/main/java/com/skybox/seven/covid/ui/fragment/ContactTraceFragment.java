package com.skybox.seven.covid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.contacts.ContactsController;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.ContactsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {

    private ContactsController controller;
    private ContactsViewModel viewModel;
    private SwipeRefreshLayout refreshLayout;

    public ContactTraceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_generic_epoxy_swipe, container, false);
        EpoxyRecyclerView recyclerView = v.findViewById(R.id.generic_recycler_id);
        refreshLayout = v.findViewById(R.id.generic_swipe_view);

        viewModel = new ViewModelProvider(getActivity(), InjectorUtil.provideContactsViewModelFactory(getContext())).get(ContactsViewModel.class);
        controller = new ContactsController();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new SpaceItemDecorator(20, true, false));
        recyclerView.setController(controller);

        refreshLayout.setOnRefreshListener(() -> viewModel.getAllContacts());

        viewModel.contactsRefresh.observe(getActivity(), aBoolean -> viewModel.getAllContacts());
        viewModel.actualContacts.observe(getViewLifecycleOwner(), contactUsersContacts -> controller.setData(false, viewModel.networkLoading.getValue(),contactUsersContacts));
        viewModel.contactsLoading.observe(getViewLifecycleOwner(), aBoolean ->{
            controller.setData(aBoolean, viewModel.networkLoading.getValue(), viewModel.actualContacts.getValue());
            if (!aBoolean) {
                refreshLayout.setRefreshing(false);
            }
        });
        viewModel.networkLoading.observe(getViewLifecycleOwner(), aBoolean -> controller.setData(viewModel.contactsLoading.getValue(), aBoolean, viewModel.actualContacts.getValue()));

        viewModel.getAllContacts();

        return v;
    }
}
