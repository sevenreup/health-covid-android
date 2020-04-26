package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.repository.ContactRequestRepository;
import com.skybox.seven.covid.ui.adapters.ContactModel;
import com.skybox.seven.covid.ui.adapters.ContactRequestAdapter;
import com.skybox.seven.covid.ui.adapters.ContactRequestModel;
import com.skybox.seven.covid.ui.adapters.contactAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactRequestFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactRequestAdapter contactRequestAdapter;
    private LinearLayoutManager layoutManager;
    public ContactRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_request, container, false);

        recyclerView = v.findViewById(R.id.contactRequestRecyclerView);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contactRequestAdapter = new ContactRequestAdapter(getMyContactRequests());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactRequestAdapter);

   return v; }

    private ArrayList<ContactRequestModel> getMyContactRequests() {
        ArrayList<ContactRequestModel> models = new ArrayList<>();
        ContactRequestModel c = new ContactRequestModel();
        c.setName("Chris Kasenda");
        c.setPhone("099449371");
        models.add(c);

        c = new ContactRequestModel();
        c.setName("Anne Kambendera");
        c.setPhone("099945545");
        models.add(c);

        c = new ContactRequestModel();
        c.setName("Anne Kambendera");
        c.setPhone("099945545");
        models.add(c);

        c = new ContactRequestModel();
        c.setName("Anne Kambendera");
        c.setPhone("099945545");
        models.add(c);

        c = new ContactRequestModel();
        c.setName("Anne Kambendera");
        c.setPhone("099945545");
        models.add(c);

        return models;
    }

}
