package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skybox.seven.covid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactTraceFragment extends Fragment {

    public ContactTraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_trace, container, false);
    }
}
