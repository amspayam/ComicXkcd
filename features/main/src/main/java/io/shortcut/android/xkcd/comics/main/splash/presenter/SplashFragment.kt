package io.shortcut.android.xkcd.comics.main.splash.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import io.shortcut.android.xkcd.comics.main.R
import io.shortcut.android.xkcd.comics.main.databinding.FragmentSplashBinding
import io.shortcut.android.xkcd.comics.uikit.base.BaseFragment
import io.shortcut.android.xkcd.comics.uikit.extension.context.getVersionApplication
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashViewModel>() {

    private val splashViewModel: SplashViewModel by viewModel()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {
        // Show version number to user for support in the future
        binding.versionTextView.text = getString(R.string.version, context?.getVersionApplication())
    }

    override fun setupObserveData() {
        splashViewModel.navigationToMainFragment.observe(viewLifecycleOwner, {
            if (it) {
                navigateToMainFragment()
            }
        })
    }

    private fun navigateToMainFragment() {
        val action = SplashFragmentDirections.actionSplashFragmentToFinderFragment()
        findNavController().navigate(action)
    }

}