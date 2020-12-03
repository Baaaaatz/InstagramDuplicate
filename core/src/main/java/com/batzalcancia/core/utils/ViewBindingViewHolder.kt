package com.batzalcancia.core.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class ViewBindingViewHolder<VB : ViewBinding>(
        val viewBinding: VB
) : RecyclerView.ViewHolder(viewBinding.root)