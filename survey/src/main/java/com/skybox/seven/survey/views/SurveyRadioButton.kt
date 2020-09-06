package com.skybox.seven.survey.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.widget.AppCompatRadioButton
import com.skybox.seven.survey.R


fun createRadioButton(text: String, tag: String, context: Context): RadioButton {
    return RadioButton(context).apply {
        setText(text)
        this.tag = tag
    }
}
