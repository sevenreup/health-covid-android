package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.StartStepFragment
import java.util.*

class StartStep(val title: String,
                private val subTile: String,
                private val buttonText: String) : Step {
    override val id: String
        get() = UUID.randomUUID().toString()

    override fun getFragment(): Fragment {
        return StartStepFragment.newInstance(title, subTile, buttonText)
    }
}