package com.skybox.seven.covid.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.adapters.ContactModel;
import com.skybox.seven.covid.epoxy.contacts.ContactsController;
import com.skybox.seven.covid.network.ContactClientInstance;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.util.InjectorUtil;
import com.skybox.seven.covid.util.SpaceItemDecorator;
import com.skybox.seven.covid.viewmodels.ContactsViewModel;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {

    private EpoxyRecyclerView recyclerView;
    ProgressDialog progressDialog;
    ContactsController controller;
    ContactsViewModel viewModel;
    RetrofitService service;

    public ContactTraceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_generic_epoxy, container, false);
        recyclerView = v.findViewById(R.id.generic_recycler_id);

        viewModel = new ViewModelProvider(getActivity(), InjectorUtil.provideContactsViewModelFactory(getContext())).get(ContactsViewModel.class);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        viewModel.contactsRefresh.observe(getActivity(), aBoolean -> generateContactList());

        controller = new ContactsController();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new SpaceItemDecorator(20, true, false));
        recyclerView.setController(controller);

        service = ContactClientInstance.getRetrofitInstance().create(RetrofitService.class);
        generateContactList();

   return v; }


    private void generateContactList(){
        Call<ArrayList<ContactModel.ContactUsersContacts>> call = service.getAllContacts(viewModel.getToken());

        call.enqueue(new Callback<ArrayList<ContactModel.ContactUsersContacts>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactModel.ContactUsersContacts>> call, Response<ArrayList<ContactModel.ContactUsersContacts>> response)
            {
                progressDialog.dismiss();
                controller.setData(false, response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<ContactModel.ContactUsersContacts>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
