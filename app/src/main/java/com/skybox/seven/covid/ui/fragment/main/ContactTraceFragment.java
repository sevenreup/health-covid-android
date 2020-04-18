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
import com.skybox.seven.covid.ui.adapters.ContactModel;
import com.skybox.seven.covid.ui.adapters.contactAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {
    private RecyclerView recyclerView;
    private contactAdapter ContactAdapter;
    private LinearLayoutManager layoutManager;
    Button addContButton;

    public ContactTraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact_trace2, container, false);
        recyclerView = v.findViewById(R.id.contactRecyclerView);
        addContButton = v.findViewById(R.id.addContButton);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ContactAdapter = new contactAdapter(getMyContacts());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ContactAdapter);


      // addContButton.setOnClickListener(v12 -> {
        //  Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.fragment_contact_trace2)};

        addContButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.createContacts);
            }
            //ContactCreateFragment contactCreateFragment = new ContactCreateFragment();
            // FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            //transaction.replace(R.id.container, contactCreateFragment);
            //transaction.commit();
            });


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
