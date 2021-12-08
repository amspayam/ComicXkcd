package io.shortcut.android.xkcd.comics.uikit.base.adapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHorizontalDecorator(
    private val margin: Int,
    private val withoutTop: Boolean = false,
    private val withoutBottom: Boolean = false,
    private val withoutFirstItemRight: Boolean = false,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (!withoutTop)
            outRect.top = margin

        if (!withoutBottom)
            outRect.bottom = margin

        outRect.left = margin

        if (!withoutFirstItemRight && position == 0) {
            outRect.right = margin
        }
    }

}