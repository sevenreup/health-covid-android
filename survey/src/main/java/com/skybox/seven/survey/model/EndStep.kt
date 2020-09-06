package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.EndStepFragment
import java.util.*

class EndStep(val title: String,
              private val subtitle: String,
              private val buttonText: String) : Step {
    override val id: String
        get() = UUID.randomUUID().toString()

    override fun getFragment(): Fragment {
        return EndStepFragment.newInstance(title, subtitle, buttonText)
    }
}