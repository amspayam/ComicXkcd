package io.shortcut.android.xkcd.comics.main.mainactivity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import io.shortcut.android.xkcd.comics.main.R
import io.shortcut.android.xkcd.comics.main.databinding.ActivityMainBinding
import io.shortcut.android.xkcd.comics.uikit.base.BaseActivity
import io.shortcut.android.xkcd.comics.uikit.base.viewmodel.BaseViewModel
import io.shortcut.android.xkcd.comics.uikit.extension.view.gone
import io.shortcut.android.xkcd.comics.uikit.extension.view.visible

class MainActivity : BaseActivity<BaseViewModel>() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.bottomNavigationView.setupWithNavController(navController)
        setupNavController()


        setContentView(binding.root)


    }

    override fun onBackPressed() {
        if (binding.bottomNavigationView.selectedItemId == R.id.explorerFragment) {
            finish()
        }
        super.onBackPressed()
    }


    override fun setupViews() {

    }

    private fun setupNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visible()
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.gone()
    }

}