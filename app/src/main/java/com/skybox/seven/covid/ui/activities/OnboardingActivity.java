package com.skybox.seven.covid.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.OnBoardingItem;
import com.skybox.seven.covid.adapters.OnBoardingAdapter;
import com.skybox.seven.covid.util.OnBoardingPageTransformer;
import com.skybox.seven.covid.viewmodels.factories.CovidFactory;
import com.skybox.seven.covid.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        viewPager2.setPageTransformer(new OnBoardingPageTransformer());

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> { }).attach();
        viewModel.changeLanguage.observe(this, this::setLanguage);
    }

    private void setOnboardingItems() {
        OnBoardingItem first = new OnBoardingItem(
                "Welcome to the Covid 19 app.",
                "Get the right info about the Covid 19 pandemic as well as self-testing without ever going outside right in the palm of your hand.\n",
                0,
                OnBoardingItem.normal
        );
        onBoardingItems.add(first);
        OnBoardingItem second = new OnBoardingItem(
                "Change language",
                "",
                0,
                OnBoardingItem.language
        );
        onBoardingItems.add(second);
        OnBoardingItem fourth = new OnBoardingItem(
                "Sign up/ Sign in or skip",
                "",
                0,
                OnBoardingItem.end
        );
        onBoardingItems.add(fourth);
    }

    @Override
    public void onClickLogin() {
        viewModel.setOnBoardingInfo(true);
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra("register", false);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClickRegister() {
        viewModel.setOnBoardingInfo(true);
        Intent intent = new Intent(this, AuthActivity.class);
        intent.putExtra("register", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClickSkip() {
        viewModel.setOnBoardingInfo(true);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void onLanguageChange(Locale locale) {
        viewModel.changeLanguage.setValue(locale);
    }
}
