package com.ysshin.fine_dust_app.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ysshin.fine_dust_app.utils.ImageConverter
import com.ysshin.fine_dust_app.utils.TextConverter

@BindingAdapter("bind_background_image")
fun ConstraintLayout.bindBackgroundImageResource(dustState: Int) {
    background = ContextCompat.getDrawable(context, ImageConverter.convertToBackgroundImage(dustState))
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

@BindingAdapter("bind_image")
fun ImageView.bindImage(dustState: Int) {
    setImageResource(ImageConverter.convertToMainIcon(dustState))
}

@BindingAdapter("bind_bottom_fine_dust_image")
fun ImageView.bindFineDustImage(fineDustValue: Int) {
    Log.d("finedustvalue", "$fineDustValue")
    setImageResource(ImageConverter.convertFineDustToIcon(fineDustValue))
}

@BindingAdapter("bind_bottom_ultra_fine_dust_image")
fun ImageView.bindUltraFineDustImage(ultraFineDustValue: Int) {
    Log.d("ULTRAfinedustvalue", "$ultraFineDustValue")
    setImageResource(ImageConverter.convertUltraFineDustToIcon(ultraFineDustValue))
}
