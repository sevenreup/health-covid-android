package com.skybox.seven.covid.ui.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.epoxy.prevention.PreventionController;
import com.skybox.seven.covid.ui.common.ShareDialogDialog;
import com.skybox.seven.covid.util.SpaceItemDecorator;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PreventionFragment extends Fragment implements PreventionController.PreventionCallback, View.OnClickListener {

    private PreventionController controller;
    private PreventionViewModel viewModel;

    public PreventionFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PreventionViewModel.class);
        controller = new PreventionController(this);

        viewModel.adviceList.observe(this, preventionModel -> controller.setData(false,preventionModel));

        viewModel.getAdviceList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prevention, container, false);
        EpoxyRecyclerView recyclerView = v.findViewById(R.id.PreventionRecyclerView);

        recyclerView.addItemDecoration(new SpaceItemDecorator(20, true, false));
        recyclerView.setController(controller);

        Button backBtn = v.findViewById(R.id.backBtnn);
        backBtn.setOnClickListener((View.OnClickListener) this);

        return v;
    }

    @Override
    public void preventionClick(Advice advice) {
        ShareDialogDialog.newInstance(advice).show(getChildFragmentManager(), advice.getTitle());
    }

    public void onClick(View view){
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
    }
}