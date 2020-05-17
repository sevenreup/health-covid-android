package com.skybox.seven.covid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.skybox.seven.covid.R
import kotlinx.android.synthetic.main.fragment_cases.*


class CasesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        return inflater.inflate(R.layout.fragment_cases, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = pageAdapter(childFragmentManager)

        adapter.addFragment(statsFragment(), "Total")
        adapter.addFragment(statsFragment(), "Today")
        adapter.addFragment(statsFragment(), "Yesterday")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
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

    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}