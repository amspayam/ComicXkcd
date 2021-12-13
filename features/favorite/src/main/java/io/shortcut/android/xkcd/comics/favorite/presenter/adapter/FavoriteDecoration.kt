package io.shortcut.android.xkcd.comics.favorite.presenter.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import io.shortcut.android.xkcd.comics.uikit.extension.permitive.dpToPx

class FavoriteDecoration(context: Context) : ItemDecoration() {

    private val padding0dp = 0.dpToPx(context)
    private val padding8dp = 8.dpToPx(context)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        outRect.right = padding8dp
        outRect.left = padding8dp
        outRect.bottom = padding8dp

        outRect.top = if (position == 0) padding8dp else padding0dp
    }

}