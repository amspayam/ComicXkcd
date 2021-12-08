package io.shortcut.android.xkcd.comics.uikit.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out T : Any>(view: View) : RecyclerView.ViewHolder(view) {
    lateinit var listener: (BaseAction) -> Unit
    abstract fun bind(data: @UnsafeVariance T)
}