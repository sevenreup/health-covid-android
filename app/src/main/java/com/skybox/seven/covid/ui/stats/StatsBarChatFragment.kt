package com.skybox.seven.covid.ui.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.ui.stats.malawian_stats.DeathsFragment
import kotlinx.android.synthetic.main.fragment_stats_bar_chat.*


class StatsBarChatFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_stats_bar_chat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pageController()
    }

    private fun pageController(){

        val pager: ViewPager? = view?.findViewById(R.id.statsViewPager)


        val adapter = pageAdapter(childFragmentManager)


        adapter.addFragment(DeathsFragment())

        pager?.adapter = adapter

        pager?.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }
        })

    }

}


class pageAdapter(manager: FragmentManager): FragmentStatePagerAdapter(manager) {

    private var fragmentList: MutableList<Fragment> = ArrayList()
    private var titleList: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}



