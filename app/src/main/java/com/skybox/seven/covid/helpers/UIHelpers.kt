package com.skybox.seven.covid.helpers

import androidx.annotation.MenuRes
import androidx.navigation.NavController
import com.google.android.material.appbar.MaterialToolbar

object UIHelpers {
    fun setUpToolbar(toolbar: MaterialToolbar, nav: NavController, @MenuRes menu: Int?, listener: androidx.appcompat.widget.Toolbar.OnMenuItemClickListener) {
        if (menu != null) {
            toolbar.inflateMenu(menu)
            toolbar.setOnMenuItemClickListener(listener)
        }
        toolbar.setNavigationOnClickListener {
            nav.navigateUp()
        }
    }
}