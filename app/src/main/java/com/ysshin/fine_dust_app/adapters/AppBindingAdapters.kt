package com.ysshin.fine_dust_app.adapters

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ysshin.fine_dust_app.utils.ImageUtils

@BindingAdapter("bind_background_image")
fun ConstraintLayout.bindBackgroundImageResource(dustState: Int) {
    background = ContextCompat.getDrawable(context, ImageUtils.convertStateToImage(dustState))
}
