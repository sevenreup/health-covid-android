package com.skybox.seven.covid.ui.mythbusters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.epoxy.mythbuster.MythBusterController;
import com.skybox.seven.covid.ui.common.ShareDialogDialog;
import com.skybox.seven.covid.util.SpaceItemDecorator;

public class MythBustersFragment extends Fragment implements MythBusterController.MythCallbacks, View.OnClickListener {

    private MythBusterController controller;
    private MythViewModel viewModel;

    public MythBustersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MythViewModel.class);
        controller = new MythBusterController(this);
        viewModel.mythsList.observe(this, mythbusterModel -> controller.setData(false,mythbusterModel));
        viewModel.getAllMyths();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_myth_busters, container, false);
        EpoxyRecyclerView recyclerView = v.findViewById(R.id.MythRecyclerView);

        recyclerView.addItemDecoration(new SpaceItemDecorator(20, true, false));
        recyclerView.setController(controller);

        Button backBtn = v.findViewById(R.id.backBtn);
        backBtn.setOnClickListener((View.OnClickListener) this);
        return v;
    }

    @Override
    public void onMythClicked(Myth myth) {
        ShareDialogDialog.newInstance(myth).show(getChildFragmentManager(), myth.getTitle());
    }

    public void onClick(View view){
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
    }
}