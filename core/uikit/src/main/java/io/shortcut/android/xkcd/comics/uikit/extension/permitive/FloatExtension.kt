package io.shortcut.android.xkcd.comics.uikit.extension.permitive

import android.content.Context
import android.util.TypedValue

fun Float.pxToDp(context: Context?): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this,
        context?.resources?.displayMetrics
    )
}

fun Float.dpToPx(context: Context?): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context?.resources?.displayMetrics
    )
}

fun Float.spToPx(context: Context?): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context?.resources?.displayMetrics
    )
}

fun Float.pxToSp(context: Context?): Float {
    val scaledDensity = context?.resources?.displayMetrics?.scaledDensity
    return scaledDensity?.let {
        (this / scaledDensity)
    } ?: 0.0f
}

