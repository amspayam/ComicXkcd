package io.shortcut.android.xkcd.comics.favorite.presenter.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.favorite.R
import io.shortcut.android.xkcd.comics.favorite.databinding.ItemFavoriteBinding
import io.shortcut.android.xkcd.comics.favorite.presenter.adapter.viewholder.FavoriteViewHolder
import io.shortcut.android.xkcd.comics.uikit.extension.inflate

class FavoriteAdapter(
    private val onClickItemListener: (position: Int, item: FavoriteEntity) -> Unit,
    private val onClickDeleteButtonsListener: (position: Int, item: FavoriteEntity) -> Unit,
    private val onClickShareButtonsListener: (position: Int, item: FavoriteEntity) -> Unit
) : PagingDataAdapter<FavoriteEntity, FavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(
                oldHistory: FavoriteEntity, newHistory: FavoriteEntity
            ): Boolean {
                return oldHistory.comicNumber == newHistory.comicNumber
            }

            override fun areContentsTheSame(
                oldHistory: FavoriteEntity, newHistory: FavoriteEntity
            ): Boolean {
                return oldHistory == newHistory
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            binding = ItemFavoriteBinding.bind(parent.inflate(R.layout.item_favorite)),
            onClickItemListener = onClickItemListener,
            onClickDeleteButtonsListener = onClickDeleteButtonsListener,
            onClickShareButtonsListener = onClickShareButtonsListener
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTo(it)
        }
    }

}


