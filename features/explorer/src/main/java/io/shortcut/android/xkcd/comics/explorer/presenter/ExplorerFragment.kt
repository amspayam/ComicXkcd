package io.shortcut.android.xkcd.comics.explorer.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.shortcut.android.xkcd.comics.explorer.databinding.FragmentExplorerBinding
import io.shortcut.android.xkcd.comics.uikit.base.BaseFragment
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
    }

    override fun setupListener() {
    }

    override fun onClick(v: View?) {
    }

}