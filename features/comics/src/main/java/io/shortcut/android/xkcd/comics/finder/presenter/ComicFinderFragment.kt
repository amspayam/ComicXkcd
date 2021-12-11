package io.shortcut.android.xkcd.comics.finder.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import io.shortcut.android.xkcd.comics.base.view.onViewData
import io.shortcut.android.xkcd.comics.base.view.onViewError
import io.shortcut.android.xkcd.comics.base.view.onViewLoading
import io.shortcut.android.xkcd.comics.finder.R
import io.shortcut.android.xkcd.comics.finder.databinding.FragmentComicFinderBinding
import io.shortcut.android.xkcd.comics.finder.domain.model.ComicModel
import io.shortcut.android.xkcd.comics.uikit.base.BaseFragment
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageMaster
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageTypeEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicFinderFragment : BaseFragment<ComicFinderViewModel>() {

    override val viewModel: ComicFinderViewModel by viewModel()
    private lateinit var binding: FragmentComicFinderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicFinderBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun setupViews() {}

    override fun setupToolbar() {
    }

    override fun setupObserveData() {
        // <editor-fold desc="Observe Last Comic">
        viewModel.comicStateViewLiveData.observe(viewLifecycleOwner) { result ->
            result
                .onViewLoading { binding.swipeRefresh.isRefreshing = true }
                .onViewData { comic ->
                    showData(comic)
                    binding.swipeRefresh.isRefreshing = false
                }.onViewError { status, messages ->
                    showMessage(
                        MessageMaster(
                            type = MessageTypeEnum.SNACK_BAR,
                            message = "$status $messages"
                        )
                    )
                    binding.swipeRefresh.isRefreshing = false
                }
        }
        // </editor-fold>

        // <editor-fold desc="Observe ComicController Buttons States">
        viewModel.comicControllerStateViewLiveData.observe(viewLifecycleOwner) { isEnabled ->
            comicControllerViewEnabled(isEnabled)
        }
        // </editor-fold>

        // <editor-fold desc="Observe Next and Last Button State">
        viewModel.nextStateViewLiveData.observe(viewLifecycleOwner) { isEnabled ->
            binding.nextComicImageView.isEnabled = isEnabled
            binding.lastComicImageView.isEnabled = isEnabled
        }
        // </editor-fold>

        // <editor-fold desc="Observe Previous and First Button State">
        viewModel.previousStateViewLiveData.observe(viewLifecycleOwner) { isEnabled ->
            binding.previousComicImageView.isEnabled = isEnabled
            binding.firstComicImageView.isEnabled = isEnabled
        }
        // </editor-fold>
    }

    override fun setupListener() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getLastComic()
        }

        onClickListeners(
            binding.comicCardView,
            binding.firstComicImageView,
            binding.previousComicImageView,
            binding.nextComicImageView,
            binding.lastComicImageView,
            binding.randomComicImageView
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.comicCardView -> navigateToDetailFragment(comic = viewModel.latestComic)
            R.id.firstComicImageView -> viewModel.firstComic()
            R.id.previousComicImageView -> viewModel.previousComic(
                comicNumber = viewModel.latestComic.number,
                firstComicNumber = viewModel.firstComicNumber
            )
            R.id.nextComicImageView -> viewModel
                .nextComic(
                    comicNumber = viewModel.latestComic.number,
                    lastComicNumber = viewModel.lastComicNumber
                )
            R.id.lastComicImageView -> viewModel.getLastComic()
            R.id.randomComicImageView -> viewModel.randomComic()
        }
    }

    private fun comicControllerViewEnabled(enabled: Boolean) {
        binding.randomComicImageView.isEnabled = enabled
        binding.favoriteComicImageView.isEnabled = enabled
        binding.shareComicImageView.isEnabled = enabled
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

    private fun navigateToDetailFragment(comic: ComicModel) {
        val action = ComicFinderFragmentDirections.actionFinderFragmentToDetailFragment(
            comic.number,
            comic.title,
            comic.imageLink,
            comic.description
        )
        findNavController().navigate(action)
    }

}