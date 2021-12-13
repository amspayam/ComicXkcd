package io.shortcut.android.xkcd.comics.favorite.presenter.adapter.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.shortcut.android.xkcd.comics.database.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.favorite.databinding.ItemFavoriteBinding

class FavoriteViewHolder(
    val binding: ItemFavoriteBinding,
    private val onClickItemListener: (position: Int, item: FavoriteEntity) -> Unit,
    private val onClickDeleteButtonsListener: (position: Int, item: FavoriteEntity) -> Unit,
    private val onClickShareButtonsListener: (position: Int, item: FavoriteEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindTo(favoriteEntity: FavoriteEntity) {
        // Image
        Glide.with(binding.comicNumberView.context)
            .load(favoriteEntity.comicImageLink)
            .into(binding.comicImageView)

        // Title
        binding.comicTitleTextView.text = favoriteEntity.comicName
        // Number
        binding.comicNumberView.text = "# ${favoriteEntity.comicNumber}"
        // Description
        binding.comicDescTextView.text = favoriteEntity.comicDescription

        // Share ClickListener
        binding.shareFavoriteImageView.setOnClickListener {
            onClickShareButtonsListener.invoke(bindingAdapterPosition, favoriteEntity)
        }

        // Delete ClickListener
        binding.deleteFavoriteImageView.setOnClickListener {
            onClickDeleteButtonsListener.invoke(bindingAdapterPosition, favoriteEntity)
        }

        // root ClickListener
        binding.root.setOnClickListener {
            onClickItemListener.invoke(bindingAdapterPosition, favoriteEntity)
        }
    }
}