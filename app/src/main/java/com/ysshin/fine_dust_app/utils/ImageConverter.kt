package com.ysshin.fine_dust_app.utils

import com.ysshin.fine_dust_app.R

object ImageConverter {
    private val backgroundImageList = listOf(
        R.drawable.bg_good,
        R.drawable.bg_moderate,
        R.drawable.bg_unhealthy,
        R.drawable.bg_very_unhealthy
    )
    private val mainIconList = listOf(
        R.drawable.main_icon_good,
        R.drawable.main_icon_moderate,
        R.drawable.main_icon_unhealthy,
        R.drawable.main_icon_very_unhealthy,
    )

    fun convertToBackgroundImage(dustState: Int) = backgroundImageList[dustState]

    fun convertToMainIcon(dustState: Int) = mainIconList[dustState]
}