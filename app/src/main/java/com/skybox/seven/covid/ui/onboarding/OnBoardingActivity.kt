package com.skybox.seven.covid.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.skybox.seven.covid.R
import com.skybox.seven.covid.adapters.OnBoardingAdapter
import com.skybox.seven.covid.adapters.OnBoardingAdapter.OnBoardCallback
import com.skybox.seven.covid.model.OnBoardingItem
import com.skybox.seven.covid.ui.AuthActivity
import com.skybox.seven.covid.ui.HomeActivity
import com.skybox.seven.covid.util.Constants
import com.skybox.seven.covid.util.OnBoardingPageTransformer
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity(), OnBoardCallback {
    val viewModel: OnBoardingViewModel by viewModels()
    private var onBoardingItems: MutableList<OnBoardingItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setOnBoardingItems()
        val viewPager2 = findViewById<ViewPager2>(R.id.intro_pager)
        val adapter = OnBoardingAdapter(onBoardingItems, this)
        val tabLayout = findViewById<TabLayout>(R.id.into_tab_layout)
        viewPager2.adapter = adapter
        viewPager2.setPageTransformer(OnBoardingPageTransformer())
        TabLayoutMediator(tabLayout, viewPager2) { _: TabLayout.Tab?, _: Int -> }.attach()
    }

    private fun setOnBoardingItems() {
        val first = OnBoardingItem(
                "Welcome to the Covid 19 app.",
                "Get the right info about the Covid 19 pandemic as well as self-testing without ever going outside right in the palm of your hand.\n",
                0,
                OnBoardingItem.normal
        )
        onBoardingItems.add(first)
        val second = OnBoardingItem(
                "Change language",
                "",
                0,
                OnBoardingItem.language
        )
        onBoardingItems.add(second)
        val fourth = OnBoardingItem(
                "Sign up/ Sign in or skip",
                "",
                0,
                OnBoardingItem.end
        )
        onBoardingItems.add(fourth)
    }

    override fun onClickLogin() {
        viewModel.setOnBoardingInfo(true)
        val intent = Intent(this, AuthActivity::class.java)
        intent.putExtra("register", false)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun onClickRegister() {
        viewModel.setOnBoardingInfo(true)
        val intent = Intent(this, AuthActivity::class.java)
        intent.putExtra("register", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun onClickSkip() {
        viewModel.setOnBoardingInfo(true)
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finishAffinity()
    }

    override fun onLanguageChange(id: Int) {
        viewModel.setLanguage(id )
        if (id == Constants.ENGLISH) {
            Lingver.getInstance().setLocale(this, Locale("eng", "USA"))
        } else {
            Lingver.getInstance().setLocale(this, Locale("ny", "MW"))
        }
        restart()
    }

    private fun restart() {
        // Todo Just close this and start main activity or send args to switch to the specific pager
        val i = Intent(this, OnBoardingActivity::class.java)
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}