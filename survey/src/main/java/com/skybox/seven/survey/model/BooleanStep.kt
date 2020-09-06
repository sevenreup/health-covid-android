package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.BooleanFragment
import java.util.*

class BooleanStep(val title: String,
                  private val subTile: String): Step {
    override val id: String
        get() = UUID.randomUUID().toString()

    override fun getFragment(): Fragment {
        return BooleanFragment.newInstance(title, subTile)
    }
}