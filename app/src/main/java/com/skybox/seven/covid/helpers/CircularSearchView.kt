package com.skybox.seven.covid.helpers

import android.animation.Animator
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.skybox.seven.covid.databinding.LayoutCircularSearchViewBinding

class CircularSearchView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
        FrameLayout(context, attrs, defStyleAttr) {
    private val binding: LayoutCircularSearchViewBinding = LayoutCircularSearchViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var callbacks: CircularCallbacks? = null

    init {
        binding.close.setOnClickListener {
            callbacks?.onSearchClose()
            closeSearch()
        }
        binding.open.setOnClickListener {
            callbacks?.onSearchOpen()
            openSearch()
        }
    }

    private fun openSearch() {
        binding.searchView.visibility = View.VISIBLE
        binding.searchClosed.visibility = View.GONE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            openCircular()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun openCircular() {
        val circularReveal = ViewAnimationUtils.createCircularReveal(binding.searchView,
            (binding.open.right + binding.open.left) / 2,
            (binding.open.top + binding.open.bottom) / 2,
            0f, width.toFloat())
        circularReveal.duration = 300
        circularReveal.start()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun closeCircular() {
        val circularConceal = ViewAnimationUtils.createCircularReveal(
                binding.searchView,
                (binding.open.right + binding.open.left) / 2,
                (binding.open.top + binding.open.bottom) / 2,
                width.toFloat(), 0f
        )

        circularConceal.duration = 300
        circularConceal.start()
        circularConceal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) = Unit
            override fun onAnimationEnd(animation: Animator?) {
                binding.searchView.visibility = View.GONE
                circularConceal.removeAllListeners()
            }
        })
    }

    fun closeSearch() {
        binding.searchClosed.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            closeCircular()
        } else {
            binding.searchView.visibility = View.GONE
        }
    }

    fun setCircularCallbacks(callbacks: CircularCallbacks) {
        this.callbacks = callbacks
    }

    interface CircularCallbacks {
        fun onSearchOpen()
        fun onSearchClose()
    }
}