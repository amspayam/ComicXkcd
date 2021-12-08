package io.shortcut.android.xkcd.comics.uikit.base.adapter.decorator

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.horizontalDecorator(
    margin: Int,
    withoutTop: Boolean = false,
    withoutBottom: Boolean = false,
    withoutFirstItemRight: Boolean = false
) {
    if (this.itemDecorationCount > 0)
        this.removeItemDecorationAt(0)
    this.addItemDecoration(
        RecyclerViewHorizontalDecorator(
            margin,
            withoutTop,
            withoutBottom,
            withoutFirstItemRight
        )
    )
}

fun RecyclerView.verticalDecorator(
    margin: Int,
) {
    if (this.itemDecorationCount > 0)
        this.removeItemDecorationAt(0)
    this.addItemDecoration(RecyclerViewVerticalDecorator(margin))
}