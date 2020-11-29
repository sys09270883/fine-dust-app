package com.ysshin.fine_dust_app.utils

import com.ysshin.fine_dust_app.R

object ImageUtils {
    private val backgroundImageMapper = hashMapOf(
        DustState.GOOD to R.drawable.bg_good,
        DustState.MODERATE to R.drawable.bg_moderate,
        DustState.UNHEALTHY to R.drawable.bg_unhealthy,
        DustState.VERY_UNHEALTHY to R.drawable.bg_very_unhealthy
    )

    fun convertStateToImage(dustState: DustState) = backgroundImageMapper[dustState]!!

}