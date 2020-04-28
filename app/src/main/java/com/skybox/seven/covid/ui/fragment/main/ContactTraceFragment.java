package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.adapters.ContactModel;
import com.skybox.seven.covid.ui.adapters.contactAdapter;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.skybox.seven.covid.ui.adapters.contactAdapter.CONTACT_LIST;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {
    private RecyclerView recyclerView;
    private contactAdapter ContactAdapter;
    private LinearLayoutManager layoutManager;
    public ContactTraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact_trace2, container, false);
        recyclerView = v.findViewById(R.id.contactRecyclerView);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ContactAdapter = new contactAdapter(getMyContacts(),CONTACT_LIST);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ContactAdapter);

   return v; }

    private ArrayList<ContactModel> getMyContacts() {
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
    }

}
