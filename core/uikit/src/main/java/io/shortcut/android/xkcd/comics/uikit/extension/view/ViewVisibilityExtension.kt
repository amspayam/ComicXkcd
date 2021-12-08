package io.shortcut.android.xkcd.comics.uikit.extension.view

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) this.visible() else this.gone()
}

fun View.visibleOrInvisible(visible: Boolean) {
    if (visible) this.visible() else this.invisible()
}