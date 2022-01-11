package com.indramahkota.common.utils

import android.content.Context
import android.util.TypedValue

fun Number.dpToPx(context: Context? = null): Float =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context?.resources?.displayMetrics
    )