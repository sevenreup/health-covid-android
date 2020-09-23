package com.skybox.seven.covid.util

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.createBitmap
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.card.MaterialCardView
import java.io.File
import java.io.FileOutputStream
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun Calendar.takeLast(days: Int) = run {
    time = Date()
    add(Calendar.DAY_OF_MONTH, -days)
    return@run Date(timeInMillis)
}

fun String.convertZuluToTargetFormat(targetFormat: String): String = run {
    val zuluTime = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
    ).parse(this) ?: Date()

    return@run SimpleDateFormat(targetFormat, Locale.getDefault())
            .format(zuluTime)
}

fun Long.shortValue(): String = run {
    val decimalFormat = NumberFormat.getNumberInstance(Locale.getDefault()) as DecimalFormat
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val value = floor(log10(this.toDouble())).toInt()
    val base = value / 3

    return@run if (value >= 3 && base < suffix.size) {
        decimalFormat.applyPattern("#0.0")
        decimalFormat.format(this / 10.0.pow(base * 3.toDouble())) + suffix[base]
    } else {
        decimalFormat.applyPattern("#,##0")
        decimalFormat.format(this)
    }
}

fun Int.toNumber() : String = NumberFormat.getInstance().format(this)

fun Double.toPercentString(): String =
        DecimalFormat("#.##'%'", DecimalFormatSymbols(Locale.getDefault())).format(this)

fun RequestManager.loadImage(url: String, isPreloading: Boolean): RequestBuilder<Bitmap> {

    val options = RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.ALL)
            .dontAnimate()
            .signature(ObjectKey(url.plus(if (isPreloading) "_preloading" else "_not_preloading")))

    return asBitmap()
            .apply(options)
            .load(url)
}

fun MaterialCardView.obtainStyledAttributes(attrsSet: AttributeSet?, attrsId: IntArray): TypedArray {
    return context.theme.obtainStyledAttributes(attrsSet, attrsId, 0, 0)
}

fun View.toImage(): Bitmap? {
    val returnedBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
    val canvas = Canvas(returnedBitmap)
    val drawable = this.background;
    if (drawable != null)  drawable.draw(canvas)
    else canvas.drawColor(Color.WHITE)
    this.draw(canvas)
    return returnedBitmap
}

fun Bitmap.getLocalBitmapUri(context: Context): Uri {
    var bitURI: Uri? = null
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png")
    val out = FileOutputStream(file)
    this.compress(Bitmap.CompressFormat.PNG, 90, out)
    out.close()

    bitURI = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        FileProvider.getUriForFile(context, context.applicationContext.opPackageName + ".provider", file)
    } else {
        Uri.fromFile(file);
    }
    return bitURI
}

fun Context.vectorToBitmap(drawableID: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableID) ?: return null
    val bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}