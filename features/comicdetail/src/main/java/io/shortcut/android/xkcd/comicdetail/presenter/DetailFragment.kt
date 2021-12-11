package io.shortcut.android.xkcd.comicdetail.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import io.shortcut.android.xkcd.comicdetail.databinding.FragmentDetailBinding
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationResponseModel
import io.shortcut.android.xkcd.comics.base.view.onViewData
import io.shortcut.android.xkcd.comics.base.view.onViewError
import io.shortcut.android.xkcd.comics.base.view.onViewLoading
import io.shortcut.android.xkcd.comics.uikit.base.BaseFragment
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageMaster
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.MessageTypeEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {
        viewModel.bindArguments(args)
    }

    @SuppressLint("SetTextI18n")
    override fun setupObserveData() {
        // <editor-fold desc="Observe Explanation">
        viewModel.detailStateViewLiveData.observe(viewLifecycleOwner) { response ->
            response
                .onViewLoading { binding.swipeRefresh.isRefreshing = true }
                .onViewData { explanation ->
                    showData(explanation)
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
    }

    override fun setupListener() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.bindArguments(args)
        }
    }

    private fun showData(explanation: ExplanationResponseModel) {
        Glide.with(requireContext())
            .load(explanation.imageUrl)
            .into(binding.comicImageView)

        binding.comicTitleTextView.text = explanation.title
        binding.comicDescriptionTexView.text = explanation.descripton
        binding.comicExplanationTexView.text = explanation.explanation
    }
}