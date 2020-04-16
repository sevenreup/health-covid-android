package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.autofill.Dataset;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.adapters.ContactModel;
import com.skybox.seven.covid.ui.adapters.contactAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter ContactAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public ContactTraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_trace2, container, false);
        recyclerView = v.findViewById(R.id.contactRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        ContactAdapter = new contactAdapter(getContext(), getMyContacts());
        recyclerView.setAdapter(ContactAdapter);

   return v; }

    private ArrayList<ContactModel> getMyContacts() {
        ArrayList<ContactModel> models = new ArrayList<>();

        ContactModel m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        m = new ContactModel();
        m.setName("Chisomo Kasenda");
        m.setPhone("0994479371");
        models.add(m);

        return models;
    }
    
}
