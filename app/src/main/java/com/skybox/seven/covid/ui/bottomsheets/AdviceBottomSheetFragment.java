package com.skybox.seven.covid.ui.bottomsheets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.viewmodels.AdviceViewModel;

public class AdviceBottomSheetFragment extends BottomSheetDialogFragment {

    public AdviceBottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_advice_bottom_sheet, container, false);

        AdviceViewModel viewModel = new ViewModelProvider(getParentFragment().getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AdviceViewModel.class);

        viewModel.activeAdvice.observe(getViewLifecycleOwner(), advice -> {
            ((TextView) v.findViewById(R.id.bt_title)).setText(advice.getTitle());
            ((TextView) v.findViewById(R.id.bt_answer)).setText(advice.getAdvice());
            ((TextView) v.findViewById(R.id.bt_reason)).setText(advice.getAnswer());
        });
        v.findViewById(R.id.share_tip).setOnClickListener(v12 -> {

        });
        v.findViewById(R.id.close_tip).setOnClickListener(v1 -> dismiss());
        return v;
    }
}
