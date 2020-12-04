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
    private val iconList = listOf(
        R.drawable.icon_good,
        R.drawable.icon_moderate,
        R.drawable.icon_unhealthy,
        R.drawable.icon_very_unhealthy,
    )
    private val skyIconList = listOf(
        R.drawable.sunny,
        R.drawable.mostly_cloudy,
        R.drawable.cloudy,
    )

    fun convertToBackgroundImage(dustState: Int) = backgroundImageList[dustState]

    fun convertToMainIcon(dustState: Int) = mainIconList[dustState]

    fun convertFineDustToIcon(fineDustState: Int): Int = iconList[fineDustState]

    fun convertUltraFineDustToIcon(ultraFineDustValue: Int) = iconList[ultraFineDustValue]

    fun convertToSkyImage(skyState: Int) = skyIconList[skyState]
}