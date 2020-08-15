package com.skybox.seven.covid.ui.onboarding;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate;
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.adapters.OnBoardingAdapter;
import com.skybox.seven.covid.data.AppDatabase;
import com.skybox.seven.covid.model.OnBoardingItem;
import com.skybox.seven.covid.ui.auth.AuthActivity;
import com.skybox.seven.covid.ui.main.HomeActivity;
import com.skybox.seven.covid.util.Constants;
import com.skybox.seven.covid.util.OnBoardingPageTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OnBoardingActivity extends AppCompatActivity implements OnBoardingAdapter.OnBoardCallback, OnLocaleChangedListener {
    private LocalizationActivityDelegate delegate = new LocalizationActivityDelegate(this);
    OnBoardingViewModel viewModel;
    List<OnBoardingItem> onBoardingItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        viewModel = new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(OnBoardingViewModel.class);
        setOnBoardingItems();
        ViewPager2 viewPager2 = findViewById(R.id.intro_pager);
        OnBoardingAdapter adapter = new OnBoardingAdapter(onBoardingItems, this);
        TabLayout tabLayout = findViewById(R.id.into_tab_layout);
        viewPager2.setAdapter(adapter);
        AppDatabase.initStuff(getBaseContext()).languageDAO().getAllLanguages();

        viewPager2.setPageTransformer(new OnBoardingPageTransformer());

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> { }).attach();
    }

    private void setOnBoardingItems() {
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
    public void onLanguageChange(int id) {
        if (id == Constants.ENGLISH) {
            delegate.setLanguage(this, new Locale("eng", "USA"));
        } else {
            delegate.setLanguage(this, new Locale("ny", "MW"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        delegate.onResume(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(delegate.attachBaseContext(newBase));
    }

    @Override
    public Context getApplicationContext() {
        return delegate.getApplicationContext(super.getApplicationContext());
    }

    @Override
    public Resources getResources() {
        return delegate.getResources(super.getResources());
    }

    @Override
    public void onAfterLocaleChanged() {

    }

    @Override
    public void onBeforeLocaleChanged() {

    }
}