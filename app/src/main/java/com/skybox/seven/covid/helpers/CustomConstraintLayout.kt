package com.skybox.seven.covid.helpers

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class CustomConstraintLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var visibilityListener: VisibilityListener? = null
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (changedView == this) visibilityListener?.onVisibilityChange(visibility)
    }

    fun setVisibilityListener(listener: VisibilityListener) {
        visibilityListener = listener
    }
}

interface VisibilityListener {
    fun onVisibilityChange(visibility: Int)
}