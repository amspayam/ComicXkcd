package io.shortcut.android.xkcd.comics.favorite.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.shortcut.android.xkcd.comics.base.view.onViewData
import io.shortcut.android.xkcd.comics.base.view.onViewError
import io.shortcut.android.xkcd.comics.favorite.R
import io.shortcut.android.xkcd.comics.favorite.databinding.FragmentFavoriteBinding
import io.shortcut.android.xkcd.comics.favorite.presenter.adapter.FavoriteAdapter
import io.shortcut.android.xkcd.comics.favorite.presenter.adapter.FavoriteDecoration
import io.shortcut.android.xkcd.comics.room.entity.FavoriteEntity
import io.shortcut.android.xkcd.comics.uikit.base.BaseFragment
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageMaster
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageTypeEnum
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FavoriteViewModel>() {

    override val viewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteAdapter = FavoriteAdapter(
        onClickItemListener = { _, item ->
            navigateToDetailFragment(comic = item)
        },
        onClickDeleteButtonsListener = { _, item ->
            showDeleteDialog(comicTitle = item.comicName, comicNumber = item.comicNumber)
        },
        onClickShareButtonsListener = { _, item ->
            shareComic()
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {
        binding.favoriteRecyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(FavoriteDecoration(requireContext()))
            adapter = favoriteAdapter
        }
    }

    override fun setupObserveData() {

        // <editor-fold desc="Observe FavoriteList State">
        viewModel.allFavoritesStateViewLiveData.observe(viewLifecycleOwner) { result ->
            result
                .onViewData { pageListFlow ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        pageListFlow.collectLatest { pageList ->
                            // Connect list to adapter
                            favoriteAdapter.submitData(pageList)
                        }
                    }
                }
                .onViewError { status, messages ->
                    showMessage(
                        MessageMaster(
                            type = MessageTypeEnum.SNACK_BAR,
                            message = "$status $messages"
                        )
                    )
                }
        }
        // </editor-fold>
    }

    private fun showDeleteDialog(comicTitle: String, comicNumber: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.delete_question, comicTitle))
            .setPositiveButton(getString(R.string.delete_button_label)) { dialog, _ ->
                viewModel.deleteComic(comicNumber = comicNumber)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel_button_label)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


    private fun shareComic() {
        showMessage(
            MessageMaster(
                type = MessageTypeEnum.TOAST,
                messageResourceId = R.string.not_implemented_yet,
            )
        )
    }

    private fun navigateToDetailFragment(comic: FavoriteEntity) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
            comic.comicNumber,
            comic.comicName,
            comic.comicImageLink,
            comic.comicDescription
        )
        findNavController().navigate(action)
    }

}