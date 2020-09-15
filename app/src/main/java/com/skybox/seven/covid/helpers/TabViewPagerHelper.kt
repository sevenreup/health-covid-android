package com.skybox.seven.covid.helpers

import androidx.viewpager2.widget.ViewPager2
import com.skybox.seven.covid.util.NoScrollHorizontalLayoutManager
import com.skybox.seven.covid.util.NoScrollRecyclerView

class TabViewPagerHelper(private val viewPager2: ViewPager2, private val recyclerView: NoScrollRecyclerView) {
    private var tabsAdapter: TabPagerAdapter
    private var statsPagerAdapter: StatsPagerAdapter
    private val context = recyclerView.context
    init {
        tabsAdapter = TabPagerAdapter(context)
        statsPagerAdapter = StatsPagerAdapter(context)
        recyclerView.adapter = tabsAdapter
        recyclerView.layoutManager = NoScrollHorizontalLayoutManager(context)
        viewPager2.adapter = statsPagerAdapter

    }
}