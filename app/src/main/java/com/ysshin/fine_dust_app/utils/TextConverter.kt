package com.ysshin.fine_dust_app.utils

import com.ysshin.fine_dust_app.R

object TextConverter {
    private val dustStateTextList = listOf(
        R.string.fine_dust_good,
        R.string.fine_dust_moderate,
        R.string.fine_dust_unhealthy,
        R.string.fine_dust_very_unhealthy
    )

    fun convertToText(dustState: Int) = dustStateTextList[dustState]
}