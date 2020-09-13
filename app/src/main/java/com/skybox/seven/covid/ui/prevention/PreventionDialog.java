package com.skybox.seven.covid.ui.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.databinding.DialogPreventionViewBinding;

public class PreventionDialog extends BottomSheetDialogFragment {
    public static final String ADVICE = "ADVICE";
    private Advice advice;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        advice = (Advice) requireArguments().getSerializable(ADVICE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       DialogPreventionViewBinding binding = DialogPreventionViewBinding.inflate(inflater, container, false);
        binding.preventionRVCardTitle.setText(advice.getTitle());
        binding.preventionRVCardDescription.setText(advice.getAdvice());
        return binding.getRoot();
    }

    public static PreventionDialog newInstance(Advice advice) {
        PreventionDialog dialog = new PreventionDialog();
        Bundle args = new Bundle();
        args.putSerializable(ADVICE, advice);
        dialog.setArguments(args);
        return dialog;
    }
}
