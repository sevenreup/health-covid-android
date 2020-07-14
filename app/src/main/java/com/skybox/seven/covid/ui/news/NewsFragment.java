package com.skybox.seven.covid.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.epoxy.EpoxyRecyclerView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.epoxy.news.NewsController;
import com.skybox.seven.covid.viewmodels.NewsViewModel;

/**
 * A View that holds all the news {@link Fragment}.
 */
public class NewsFragment extends Fragment {

    EpoxyRecyclerView recyclerView;
    TextView networkErrors;
    NewsController controller;
    SwipeRefreshLayout refreshLayout;
    NewsViewModel viewModel;

    public NewsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NewsViewModel.class);
        recyclerView = rootView.findViewById(R.id.recycler);
        networkErrors = rootView.findViewById(R.id.network_error);
        refreshLayout = rootView.findViewById(R.id.swipe_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        controller = new NewsController();
        recyclerView.setController(controller);

        viewModel.loading.observe(getViewLifecycleOwner(), aBoolean -> controller.setData(aBoolean, viewModel.newsArticles.getValue()));
        viewModel.networkErrors.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                networkErrors.setVisibility(View.VISIBLE);
            else
                networkErrors.setVisibility(View.GONE);
        });
        viewModel.newsArticles.observe(getViewLifecycleOwner(), articles -> controller.setData(false, articles));

        refreshLayout.setOnRefreshListener(() -> viewModel.getNews());
        viewModel.getNews();

        return rootView;
    }


}
