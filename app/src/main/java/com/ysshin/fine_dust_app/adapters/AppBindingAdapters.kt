package com.ysshin.fine_dust_app.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ysshin.fine_dust_app.utils.ImageConverter
import com.ysshin.fine_dust_app.utils.TextConverter

@BindingAdapter("bind_background_image")
fun ConstraintLayout.bindBackgroundImageResource(dustState: Int) {
    background =
        ContextCompat.getDrawable(context, ImageConverter.convertToBackgroundImage(dustState))
}

@BindingAdapter("bind_text")
fun TextView.bindText(dustState: Int) {
    setText(TextConverter.convertToText(dustState))
}

@BindingAdapter("bind_bottom_fine_dust_text")
fun TextView.bindBottomFineDustText(fineDustValue: Int) {
    text = StringBuilder("미세먼지\n${fineDustValue}ug/m").toString()
}

@BindingAdapter("bind_bottom_ultra_fine_dust_text")
fun TextView.bindBottomUltraFineDustText(ultraFineDustValue: Int) {
    text = StringBuilder("초미세먼지\n${ultraFineDustValue}ug/m").toString()
}

@BindingAdapter("bind_cold_text")
fun TextView.bindColdText(minTemperature: Double) {
    text = StringBuilder("최저\n${TextConverter.convertIntIfWhole(minTemperature)}도").toString()
}

@BindingAdapter("bind_hot_text")
fun TextView.bindHotText(maxTemperature: Double) {
    text = StringBuilder("최고\n${TextConverter.convertIntIfWhole(maxTemperature)}도").toString()
}

@BindingAdapter("bind_image")
fun ImageView.bindImage(dustState: Int) {
    setImageResource(ImageConverter.convertToMainIcon(dustState))
}

@BindingAdapter("bind_sky_image")
fun ImageView.bindSkyImage(skyState: Int) {
    setImageResource(ImageConverter.convertToSkyImage(skyState))
}

@BindingAdapter("bind_bottom_fine_dust_image")
fun ImageView.bindFineDustImage(fineDustValue: Int) {
    setImageResource(ImageConverter.convertFineDustToIcon(fineDustValue))
}

@BindingAdapter("bind_bottom_ultra_fine_dust_image")
fun ImageView.bindUltraFineDustImage(ultraFineDustValue: Int) {
    setImageResource(ImageConverter.convertUltraFineDustToIcon(ultraFineDustValue))
}

@BindingAdapter("bind_visibility")
fun ProgressBar.bindVisibility(loading: Boolean) {
    visibility = when (loading) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}
