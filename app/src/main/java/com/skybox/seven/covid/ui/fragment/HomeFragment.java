package com.skybox.seven.covid.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.MainController;
import com.skybox.seven.covid.model.MenuItem;
import com.skybox.seven.covid.ui.activities.AuthActivity;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements MainController.MainControllerCallback {

    private MainViewModel viewModel;
    private EpoxyRecyclerView recyclerView;
    private MainController controller;
    private List<MenuItem> menuItems = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(getActivity(), new CovidFactory(getActivity().getApplication())).get(MainViewModel.class);

        controller = new MainController(this);
        recyclerView = v.findViewById(R.id.home_frag_recycler);

        recyclerView.setController(controller);
        createMenuItems();
        controller.setData(viewModel.isLoggedIn(),viewModel.showLoginNotification.getValue() ,menuItems);

        viewModel.showLoginNotification.observe(getViewLifecycleOwner(), show -> controller.setData(viewModel.isLoggedIn(),show ,menuItems));

        return v;
    }

    private void createMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.drawable.ic_user, R.string.title_health_tips, R.id.healthFragment, 0));
        menuItems.add(new MenuItem(R.drawable.ic_worlds, R.string.title_mythbusters, R.id.qanAFragment, 1));
        menuItems.add(new MenuItem(R.drawable.ic_newspaper, R.string.title_news, R.id.newsFragment, 3));
        menuItems.add(new MenuItem(R.drawable.ic_history, R.string.title_qna, R.id.mythBusterFragment, 4));

        if (viewModel.isLoggedIn()){
            menuItems.add(new MenuItem(R.drawable.ic_team, R.string.title_contacts, R.id.contactsFragment, 5));
        }
        this.menuItems = menuItems;
    }

    @Override
    public void OnRegisterClick() {
        Intent intent = new Intent(getContext(), AuthActivity.class);
        intent.putExtra("register", true);
        startActivity(intent);
    }

    @Override
    public void OnLoginClick() {
        Intent intent = new Intent(getContext(), AuthActivity.class);
        intent.putExtra("register", false);
        startActivity(intent);
    }

    @Override
    public void OnCloseLogNotification() {
        viewModel.showLoginNotification.setValue(false);
    }

    @Override
    public void NavigateToPage(int dest) {
        Navigation.findNavController(getActivity(), R.id.container).navigate(dest);
    }

    @Override
    public void OnSelfTestClick() {
        Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.selfTestFragment);
    }

    @Override
    public void OnPersonalCardClick() {
        Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.personalFragment);
    }

    @Override
    public void OnEmergencyContactsClick() {
        Navigation.findNavController(getActivity(), R.id.container).navigate(R.id.emergencyContactsFragment);
    }
}