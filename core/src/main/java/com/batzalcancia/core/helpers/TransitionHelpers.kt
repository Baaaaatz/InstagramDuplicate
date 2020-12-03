package com.batzalcancia.core.helpers

import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.batzalcancia.core.R
import com.google.android.material.transition.MaterialContainerTransform

fun containerTransition() = MaterialContainerTransform().apply {
    duration = 300
    containerColor = Color.WHITE
    scrimColor = Color.TRANSPARENT
    interpolator = FastOutSlowInInterpolator()
    fadeMode = MaterialContainerTransform.FADE_MODE_IN
}

fun View.setContainerTransition(identifier: String) {
    ViewCompat.setTransitionName(
        this,
        context.getString(
            R.string.container_transition_name,
            identifier
        )
    )
}

fun View.prepareReturnTransition(fragment: Fragment) {
    fragment.postponeEnterTransition()
    viewTreeObserver.addOnPreDrawListener {
        fragment.startPostponedEnterTransition()
        true
    }
}