package com.skybox.seven.covid.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.ContactClientInstance;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.network.responses.GenericResponse;
import com.skybox.seven.covid.adapters.ContactRequestAdapter;
import com.skybox.seven.covid.adapters.ContactRequestModel;
import com.skybox.seven.covid.viewmodels.factories.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactRequestFragment extends Fragment implements ContactRequestAdapter.ContactsCallBAck {

    private RecyclerView recyclerView;
    private ContactRequestAdapter contactRequestAdapter;
    private LinearLayoutManager layoutManager;
    ProgressDialog progressDialog;
    MainViewModel viewModel;
    RetrofitService service;
    ArrayList<ContactRequestModel.PendingContacts> pendingContacts = new ArrayList<>();

    public ContactRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_request, container, false);
        recyclerView = v.findViewById(R.id.contactRequestRecyclerView);

        viewModel = new ViewModelProvider(getActivity(), new CovidFactory(getActivity().getApplication())).get(MainViewModel.class);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        contactRequestAdapter = new ContactRequestAdapter(getContext(), pendingContacts, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactRequestAdapter);

        service = ContactClientInstance.getRetrofitInstance().create(RetrofitService.class);

        generatePendingContactList();

        return v;
    }


    private void generatePendingContactList() {
        Call<ArrayList<ContactRequestModel.PendingContacts>> call = service.getPendingContacts(viewModel.getToken());

        call.enqueue(new Callback<ArrayList<ContactRequestModel.PendingContacts>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactRequestModel.PendingContacts>> call, Response<ArrayList<ContactRequestModel.PendingContacts>> response) {
                progressDialog.dismiss();
                pendingContacts = response.body();
                contactRequestAdapter.setData(pendingContacts);
            }

            @Override
            public void onFailure(Call<ArrayList<ContactRequestModel.PendingContacts>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onContactAcceptClick(int id, int position) {
        Log.e("Errors", id+"");
        Call<GenericResponse> call = service.verifyContact(viewModel.getToken(), id, "accepted");
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                pendingContacts.remove(position);
                viewModel.contactsRefresh.setValue(true);
                generatePendingContactList();
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onContactRejectClick(int id, int position) {
        RetrofitService service = ContactClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<GenericResponse> call = service.verifyContact(viewModel.getToken(), getId(), "rejected");
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                pendingContacts.remove(position);
                generatePendingContactList();
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {

            }
        });
    }
}
