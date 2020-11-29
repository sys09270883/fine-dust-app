package com.ysshin.fine_dust_app.adapters

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ysshin.fine_dust_app.utils.ImageUtils

object AppBindingAdapters {
    @JvmStatic
    @BindingAdapter("bind_background_image")
    fun bindBackgroundImage(layout: ConstraintLayout, dustState: Int) {
        Log.d("dust state", "${ImageUtils.convertStateToImage(dustState)}")
        layout.background = ContextCompat.getDrawable(
            layout.context,
            ImageUtils.convertStateToImage(dustState)
        )
    }
}