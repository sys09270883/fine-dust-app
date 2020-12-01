package com.ysshin.fine_dust_app.utils

object FineDustConverter {
    fun convertToFineDustState(fineDustValue: Int): Int {
        var state = 0
        when (fineDustValue) {
            in 31..80 -> state = 1
            in 81..150 -> state = 2
            in 151..600 -> state = 3
        }
        return state
    }

    fun convertToUltraFineDustState(ultraFineDustValue: Int): Int {
        var state = 0
        when (ultraFineDustValue) {
            in 16..35 -> state = 1
            in 36..75 -> state = 2
            in 76..500 -> state = 3
        }
        return state
    }
}