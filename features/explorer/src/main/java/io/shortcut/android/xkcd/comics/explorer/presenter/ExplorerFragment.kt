package io.shortcut.android.xkcd.comics.explorer.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import io.shortcut.android.xkcd.comics.base.view.onViewData
import io.shortcut.android.xkcd.comics.base.view.onViewError
import io.shortcut.android.xkcd.comics.explorer.R
import io.shortcut.android.xkcd.comics.explorer.databinding.FragmentExplorerBinding
import io.shortcut.android.xkcd.comics.explorer.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.uikit.base.BaseFragment
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageMaster
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageTypeEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExplorerFragment : BaseFragment<ExplorerViewModel>() {

    override val viewModel: ExplorerViewModel by viewModel()
    private lateinit var binding: FragmentExplorerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExplorerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun setupViews() {}

    override fun setupToolbar() {
    }

    override fun setupObserveData() {
        // <editor-fold desc="Observe Last Comic">
        viewModel.comicStateViewLiveData.observe(viewLifecycleOwner) { result ->
            result.onViewData { comic ->
                showData(comic)
            }.onViewError { status, messages ->
                showMessage(
                    MessageMaster(
                        type = MessageTypeEnum.SNACK_BAR,
                        message = "$status $messages"
                    )
                )
            }
        }
        // </editor-fold>

        // <editor-fold desc="Observe Random Button State">
        viewModel.randomStateViewLiveData.observe(viewLifecycleOwner) { isEnabled ->
            binding.randomComicImageView.isEnabled = isEnabled
        }
        // </editor-fold>

        // <editor-fold desc="Observe Next Button State">
        viewModel.nextStateViewLiveData.observe(viewLifecycleOwner) { isEnabled ->
            binding.nextComicImageView.isEnabled = isEnabled
        }
        // </editor-fold>

        // <editor-fold desc="Observe Previous Button State">
        viewModel.previousStateViewLiveData.observe(viewLifecycleOwner) { isEnabled ->
            binding.previousComicImageView.isEnabled = isEnabled
        }
        // </editor-fold>
    }

    @SuppressLint("SetTextI18n")
    private fun showData(comic: ComicModel) {
        Glide.with(requireContext())
            .load(comic.imageLink)
            .into(binding.comicImageView)

        binding.comicTitleTextView.text = comic.title
        binding.comicNumberView.text = "# ${comic.number}"
        binding.comicDescTextView.text = comic.description
    }

    override fun setupListener() {
        onClickListeners(
            binding.firstComicImageView,
            binding.previousComicImageView,
            binding.nextComicImageView,
            binding.lastComicImageView,
            binding.randomComicImageView
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.firstComicImageView -> viewModel.getComicByNumber(comicNumber = 1)
            R.id.previousComicImageView -> viewModel
                .getComicByNumber(comicNumber = viewModel.latestComicNumber - 1)
            R.id.nextComicImageView -> viewModel
                .getComicByNumber(comicNumber = viewModel.latestComicNumber + 1)
            R.id.lastComicImageView -> viewModel
                .getComicByNumber(comicNumber = viewModel.lastComicNumber)
            R.id.randomComicImageView -> viewModel.getComicByNumber(
                comicNumber = viewModel.randomComic(
                    lastComicNumber = viewModel.lastComicNumber
                )
            )
        }
    }

}