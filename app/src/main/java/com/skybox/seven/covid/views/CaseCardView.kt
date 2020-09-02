package com.skybox.seven.covid.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.LayoutCaseCardViewBinding
import com.skybox.seven.covid.util.obtainStyledAttributes

class CaseCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        MaterialCardView(context, attrs, defStyleAttr) {
    var title: String?  = context.getString(R.string.active)
    var valueText: String? = "Here"
    set(value) {
        binding.value.text = value
        field = value
    }
    var textColor = Color.BLACK

    private val binding = LayoutCaseCardViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        handleAttrs(obtainStyledAttributes(attrs, R.styleable.CaseCardView))

        binding.title.text = title
        binding.value.text = valueText
        binding.title.setTextColor(textColor)
    }

    private fun handleAttrs(typedArray: TypedArray) {
        typedArray.apply {
            title = getString(R.styleable.CaseCardView_titleText)
            valueText = getString(R.styleable.CaseCardView_valueText)
            textColor = getColor(R.styleable.CaseCardView_titleColor, Color.BLACK)
            recycle()
        }
    }

    companion object {

    }
}