package com.batzalcancia.instagramduplicate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.batzalcancia.instagramduplicate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityMainBinding

    private val navController: NavController by lazy { findNavController(R.id.nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            viewBinding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    // For UI to go beyond the navigation bar
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    // For UI to go beyond the status bar
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        viewBinding.bottomNavMain.apply {
            setupWithNavController(navController)
            setOnNavigationItemReselectedListener { }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            viewBinding.bottomNavMain.isVisible = destination.id == R.id.dashboard
        }

        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.bottomNavMain) { v, insets ->
            v.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}