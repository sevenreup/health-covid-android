package com.skybox.seven.covid.ui.fragment.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.network.ContactClientInstance;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.ui.adapters.ContactModel;
import com.skybox.seven.covid.ui.adapters.contactAdapter;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.skybox.seven.covid.ui.adapters.contactAdapter.CONTACT_LIST;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {

    private RecyclerView recyclerView;
    private contactAdapter ContactAdapter;
    private LinearLayoutManager layoutManager;
    ProgressDialog progressDialog;
    MainViewModel viewModel;
    RetrofitService service;
    ArrayList<ContactModel.ContactUsersContacts>contactsList = new ArrayList<>();

    public ContactTraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_contact_trace2, container, false);
        recyclerView = v.findViewById(R.id.contactRecyclerView);

        viewModel = new ViewModelProvider(getActivity(), new CovidFactory(getActivity().getApplication())).get(MainViewModel.class);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        viewModel.contactsRefresh.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                generateContactList();
            }

        });

        ContactAdapter = new contactAdapter(getContext(),contactsList,CONTACT_LIST);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ContactAdapter);

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
                contactsList=response.body();
                ContactAdapter.setData(contactsList);
            }
            @Override
            public void onFailure(Call<ArrayList<ContactModel.ContactUsersContacts>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
