package com.ysshin.fine_dust_app.adapters

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

@BindingAdapter("bind_image")
fun ImageView.bindImage(dustState: Int) {
    setImageResource(ImageConverter.convertToMainIcon(dustState))
}
