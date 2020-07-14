package com.skybox.seven.covid.ui.contactTracing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.contacts.ContactsRequestController;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.ui.contactTracing.ContactsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactRequestFragment extends Fragment implements ContactsRequestController.ContactsCallBack {

    private ContactsRequestController controller;
    private ContactsViewModel viewModel;
    private SwipeRefreshLayout refreshLayout;

    public ContactRequestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_generic_epoxy_swipe, container, false);
        EpoxyRecyclerView recyclerView = v.findViewById(R.id.generic_recycler_id);
        refreshLayout = v.findViewById(R.id.generic_swipe_view);

        viewModel = new ViewModelProvider(getActivity(), InjectorUtil.provideContactsViewModelFactory(getContext())).get(ContactsViewModel.class);
        controller = new ContactsRequestController(this);

        recyclerView.setController(controller);

        refreshLayout.setOnRefreshListener(() -> viewModel.generatePendingContactList());

        viewModel.contactsRefresh.observe(getActivity(), aBoolean -> viewModel.getAllContacts());
        viewModel.pendingContacts.observe(getViewLifecycleOwner(), pendingContacts -> controller.setData(false,pendingContacts));
        viewModel.contactsLoading.observe(getViewLifecycleOwner(), aBoolean ->{
            controller.setData(aBoolean, viewModel.pendingContacts.getValue());
            if (!aBoolean) {
                refreshLayout.setRefreshing(false);
            }
        });

        viewModel.generatePendingContactList();
        return v;
    }


    @Override
    public void onContactAcceptClick(int id, int position) {
        viewModel.acceptContact(id, position);
    }

    @Override
    public void onContactRejectClick(int id, int position) {
        viewModel.rejectContact(id, position);
    }
}
