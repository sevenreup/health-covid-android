package com.skybox.seven.covid.ui.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.MainController;
import com.skybox.seven.covid.model.MenuItem;
import com.skybox.seven.covid.network.responses.LoginResponse;
import com.skybox.seven.covid.ui.ContactActivity;
import com.skybox.seven.covid.ui.HomeActivity;
import com.skybox.seven.covid.util.BaseModelFactory;
import com.skybox.seven.covid.util.GridItemDecoration;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private MainViewModel viewModel;
    EpoxyRecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(MainViewModel.class);
        MainController controller = new MainController(Navigation.findNavController(getActivity(), R.id.container));
        recyclerView = v.findViewById(R.id.home_frag_recycler);

        recyclerView.setController(controller);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridItemDecoration(getContext(), R.dimen.menu_card_margin));
        controller.setData(createMenuItems());

        viewModel.credentials.observe(getActivity(), loginResponse -> {
        });

        return v;
    }

    private List<MenuItem> createMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.drawable.ic_user, R.string.menu_health_tips, R.id.healthFragment));
        menuItems.add(new MenuItem(R.drawable.ic_worlds, R.string.menu_mythbusters, R.id.healthFragment));
        menuItems.add(new MenuItem(R.drawable.ic_test, R.string.menu_self_test, R.id.healthFragment));
        menuItems.add(new MenuItem(R.drawable.ic_team, R.string.menu_news, R.id.healthFragment));
        menuItems.add(new MenuItem(R.drawable.ic_history, R.string.menu_qna, R.id.healthFragment));
        menuItems.add(new MenuItem(R.drawable.ic_newspaper, R.string.menu_contacts, R.id.healthFragment));
        return menuItems;
    }

}
