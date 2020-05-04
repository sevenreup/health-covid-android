package com.skybox.seven.covid.ui.bottomsheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

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
        View v = inflater.inflate(R.layout.fragment_advice_bottom_sheet, container, false);
        AdviceViewModel viewModel = new ViewModelProvider(getParentFragment().getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AdviceViewModel.class);

        viewModel.activeAdvice.observe(getViewLifecycleOwner(), advice -> {
                ((TextView) v.findViewById(R.id.bt_title)).setText(advice.getTitle());
                ((TextView) v.findViewById(R.id.bt_answer)).setText(advice.getAdvice());
        });

        v.findViewById(R.id.share_tip).setOnClickListener(v12 -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, buildSharableString(viewModel.activeAdvice.getValue()));
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

        });
        v.findViewById(R.id.close_tip).setOnClickListener(v1 -> dismiss());
        return v;
    }

    private String buildSharableString(Advice advice) {
        return getString(R.string.advice_title_bt_health_tip) +
                advice.getTitle() +
                getString(R.string.advice_title_bt_health_why) +
                advice.getAdvice();
    }
}
