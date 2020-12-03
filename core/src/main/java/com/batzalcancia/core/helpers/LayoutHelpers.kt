package com.batzalcancia.core.helpers

import android.content.Context

/**
 * Gets dp value of int
 */
fun Int.dp(context: Context): Int = (this / context.resources.displayMetrics.density).toInt()

/**
 * Gets px value of int
 */
fun Int.px(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()