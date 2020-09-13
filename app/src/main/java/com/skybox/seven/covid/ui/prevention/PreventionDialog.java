package com.skybox.seven.covid.ui.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.skybox.seven.covid.databinding.DialogPreventionViewBinding;

public class PreventionDialog extends DialogFragment {
    private DialogPreventionViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogPreventionViewBinding.inflate(inflater, container, false);
        
        return binding.getRoot();
    }
}
