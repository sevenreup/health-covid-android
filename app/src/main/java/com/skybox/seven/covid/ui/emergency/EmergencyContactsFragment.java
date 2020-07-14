package com.skybox.seven.covid.ui.emergency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.emergency.EmergencyController;
import com.skybox.seven.covid.model.ContactNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Emergency contact page {@link Fragment}.
 */
public class EmergencyContactsFragment extends Fragment {
    private ChipGroup mainSelector, filterSelector;
    private boolean filterShow = false;

    HashMap<String, List<ContactNumber>> contactList = new HashMap<>();

    public EmergencyContactsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emergency_contacts, container, false);

        EpoxyRecyclerView recyclerView = v.findViewById(R.id.emergency_recycler);
        EmergencyController controller = new EmergencyController();
//        recyclerView.addItemDecoration(new MultiViewDecorator(getContext()));
        recyclerView.setController(controller);

        mainSelector = v.findViewById(R.id.emergency_filter);
        filterSelector = v.findViewById(R.id.filter_chip_group);

        addChips();
        createHashMap();
        controller.setData(contactList);

        v.findViewById(R.id.filter_button).setOnClickListener(v1 -> {
            if (filterShow) {
                filterSelector.setVisibility(View.GONE);
                filterShow = false;
            } else {
                filterSelector.setVisibility(View.VISIBLE);
                filterShow = true;
            }
        });
        mainSelector.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.chip_all_emergency:
                case R.id.chip_all_info:
                    break;
            }
        });
        return v;
    }

    private void addChips() {
        String[] genres = {"Lilongwe", "Mzuzu", "Blantyre"};
        for(String genre : genres) {
            Chip chip = new Chip(getContext());
            ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(getContext(), null, 0, R.style.Widget_MaterialComponents_Chip_Choice);
            chip.setChipDrawable(chipDrawable);
            chip.setText(genre);
            filterSelector.addView(chip);
        }
    }

    private void createHashMap() {
        List<ContactNumber> mzuzu = new ArrayList<>();
        mzuzu.add(new ContactNumber("Yoyamba", "aa", ContactNumber.RESPONSE_TEAM));
        mzuzu.add(new ContactNumber("achiwiri", "aa", ContactNumber.RESPONSE_TEAM));
        mzuzu.add(new ContactNumber("Zinthu", "aa", ContactNumber.RESPONSE_TEAM));
        mzuzu.add(new ContactNumber("Wina", "aaa", ContactNumber.RESPONSE_TEAM));
        contactList.put("Lilongwe", mzuzu);
        contactList.put("Blantyre", mzuzu);
    }
}
