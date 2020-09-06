package com.skybox.seven.survey.model

import androidx.fragment.app.Fragment
import com.skybox.seven.survey.fragments.MultiSelectionFragment
import java.util.*
import kotlin.collections.ArrayList

class MultiSelectionStep(val title: String,
                         private val subTile: String,
                         private val answers: ArrayList<String>): Step {
    override val id: String
        get() = UUID.randomUUID().toString()

    override fun getFragment(): Fragment {
        return MultiSelectionFragment.newInstance(title, subTile, answers)
    }
}