package com.batzalcancia.dashboard.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.batzalcancia.core.utils.ViewBindingViewHolder
import com.batzalcancia.dashboard.R
import com.batzalcancia.dashboard.databinding.ItemLoadingBinding

class LoadingStateAdapter(private val setupBinding: (View) -> ItemLoadingBinding) : LoadStateAdapter<ViewBindingViewHolder<ItemLoadingBinding>>() {
    override fun onBindViewHolder(
            holder: ViewBindingViewHolder<ItemLoadingBinding>,
            loadState: LoadState
    ) {
        holder.viewBinding.prgJokes.isVisible = loadState == LoadState.Loading
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            loadState: LoadState
    ): ViewBindingViewHolder<ItemLoadingBinding> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
        return ViewBindingViewHolder(setupBinding(view))
    }

}