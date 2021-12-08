package io.shortcut.android.xkcd.comics.uikit.base.adapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewVerticalDecorator(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        outRect.right = margin
        outRect.bottom = margin
        outRect.left = margin

        if (position == 0) {
            outRect.top = margin
        }
    }

}