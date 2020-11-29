package com.ysshin.fine_dust_app.utils

import com.ysshin.fine_dust_app.R

object ImageUtils {
    private val backgroundImageList = listOf(
        R.drawable.bg_good,
        R.drawable.bg_moderate,
        R.drawable.bg_unhealthy,
        R.drawable.bg_very_unhealthy
    )

    fun convertStateToImage(dustState: Int) = backgroundImageList[dustState]
}