package com.skybox.seven.covid.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.OnBoardingItem;
import com.skybox.seven.covid.ui.adapters.OnBoardingAdapter;
import com.skybox.seven.covid.viewmodels.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends LocalizationActivity implements OnBoardingAdapter.OnBoardCallback {
    MainViewModel viewModel;
    List<OnBoardingItem> onBoardingItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        viewModel = new ViewModelProvider(this, new CovidFactory(getApplication())).get(MainViewModel.class);
        setOnboardingItems();
        ViewPager2 viewPager2 = findViewById(R.id.intro_pager);
        OnBoardingAdapter adapter = new OnBoardingAdapter(onBoardingItems, this);
        TabLayout tabLayout = findViewById(R.id.into_tab_layout);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> { }).attach();
    }

    private void setOnboardingItems() {
        OnBoardingItem first = new OnBoardingItem(
                "Covid-19",
                "Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aperiam molestiae distinctio " +
                        "ullam alias nihil veritatis aliquam quisquam? Maiores facilis iure in odit, atque alias, magnam ab voluptatum enim, quis incidunt?\n",
                0,
                OnBoardingItem.normal
        );
        onBoardingItems.add(first);
        OnBoardingItem second = new OnBoardingItem(
                "The start up logo will be fixed later",
                "if you want to stop this from appearing just uncomment viewModel.setOnBoardingInfo(true); on the button clicks",
                0,
                OnBoardingItem.normal
        );
        onBoardingItems.add(second);
        OnBoardingItem third = new OnBoardingItem(
                "This will be fixed later",
                "if you want to stop this from appearing just uncomment viewModel.setOnBoardingInfo(true); on the button clicks",
                0,
                OnBoardingItem.normal
        );
        onBoardingItems.add(third);
        OnBoardingItem fourth = new OnBoardingItem(
                "This is the title",
                "if you want to stop this from appearing just uncomment viewModel.setOnBoardingInfo(true); on the button clicks",
                0,
                OnBoardingItem.end
        );
        onBoardingItems.add(fourth);
    }

    @Override
    public void onClickLogin() {
//        viewModel.setOnBoardingInfo(true);
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra("register", false);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClickRegister() {
//        viewModel.setOnBoardingInfo(true);
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra("register", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClickSkip() {
//        viewModel.setOnBoardingInfo(true);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finishAffinity();
    }
}
