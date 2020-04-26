package com.skybox.seven.covid.ui.fragment.main;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {
    private RecyclerView recyclerView;
    private contactAdapter ContactAdapter;
    private LinearLayoutManager layoutManager;
    ProgressDialog progressDialog;
    MainViewModel viewModel;

    public ContactTraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact_trace2, container, false);
        viewModel = new ViewModelProvider(getActivity(), new CovidFactory(getActivity().getApplication())).get(MainViewModel.class);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        RetrofitService service = ContactClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<ContactModel>> call = service.getAllContacts(viewModel.getToken());

        call.enqueue(new Callback<ArrayList<ContactModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactModel>> call, Response<ArrayList<ContactModel>> response) {
                progressDialog.dismiss();
                generateContactList(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ContactModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


   return v; }

    private void generateContactList(ArrayList<ContactModel>models){
        recyclerView = recyclerView.findViewById(R.id.contactRecyclerView);
        ContactAdapter = new contactAdapter(getContext(),models);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ContactAdapter);
    }


   /* private ArrayList<ContactModel> getMyContacts() {
        ArrayList<ContactModel> models = new ArrayList<>();

        ContactModel m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Madalitso Nyemba");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Christopher Phiri");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Nhlanhla Dhaka");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Kelvin Chidothi");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Benjamin Ngwenya");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Steve Chikwiri");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Yankho Mijiga");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Bryan Malunje");
        m.setPhone("0994479371");
        models.add(m);

        return models;
    }   */

}
