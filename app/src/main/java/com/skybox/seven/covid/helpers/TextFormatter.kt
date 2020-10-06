package com.skybox.seven.covid.helpers

import android.net.Uri
import java.text.NumberFormat
import java.util.*

object TextFormatter {
    fun Int?.formatNumber(): String? {
        return try {
            NumberFormat.getNumberInstance(Locale.US).format(this)
        } catch (ex: IllegalArgumentException) {
            this?.toString()
        }
    }

    fun ussdToCallableUri(ussd: String): Uri? {
        var uriString: String? = ""
        if (!ussd.startsWith("tel:")) uriString += "tel:"
        for (c in ussd.toCharArray()) {
            if (c == '#') uriString += Uri.encode("#") else uriString += c
        }
        return Uri.parse(uriString)
    }
}