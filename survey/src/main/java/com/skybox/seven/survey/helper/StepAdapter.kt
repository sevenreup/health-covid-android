package com.skybox.seven.survey.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skybox.seven.survey.model.Step

class StepAdapter(fa: FragmentActivity, private val steps: List<Step>): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = steps.size

    override fun createFragment(position: Int): Fragment =
            when(steps[position]) {
                else -> steps[position].getFragment()
            }

}