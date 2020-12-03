package com.batzalcancia.dashboard.presentation

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.batzalcancia.dashboard.R
import com.batzalcancia.dashboard.databinding.FragmentDashboardBinding
import com.batzalcancia.dashboard.databinding.ItemPictureBinding
import com.batzalcancia.core.domain.local.entities.InstagramPicture
import com.batzalcancia.dashboard.databinding.ItemLoadingBinding
import com.batzalcancia.dashboard.presentation.adapter.LoadingStateAdapter
import com.batzalcancia.dashboard.presentation.adapter.PicturesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.ByteArrayOutputStream


@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var viewBinding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()

    private val picturesAdapter by lazy {
        PicturesAdapter(ItemPictureBinding::bind)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentDashboardBinding.bind(view)
        viewBinding.rcvPhotos.adapter = picturesAdapter

        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.apbMain) { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.rcvPhotos) { v, insets ->
            v.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }

        viewBinding.btnRetry.setOnClickListener {
            findNavController().navigate(R.id.action_global_to_capture)
        }

        viewModel.pagingData.onEach {
            picturesAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        picturesAdapter.addLoadStateListener { loadState ->
            viewBinding.btnRetry.isVisible = picturesAdapter.itemCount == 0
        }

        picturesAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter(ItemLoadingBinding::bind)
        )

    }


}