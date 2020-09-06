package com.skybox.seven.survey.helper

import android.os.Bundle

private const val TITLE = "title"
private const val SUBTITLE = "subtitle"

class QuestionFragmentArgs(val bundle: Bundle) {
    var title: String? = null
    var subTitle: String? = null

    init {
        bundle.let {
            title = it.getString(TITLE)
            subTitle = it.getString(SUBTITLE)
        }
    }

    companion object {
        @JvmStatic
        fun putGenerics(title: String, subTitle: String): Bundle = Bundle().apply {
            putString(TITLE, title)
            putString(SUBTITLE, subTitle)
        }
    }
}