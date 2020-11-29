package com.ysshin.fine_dust_app.adapters

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import com.ysshin.fine_dust_app.utils.DustState
import com.ysshin.fine_dust_app.utils.ImageUtils

object AppBindingAdapters {
    @JvmStatic
    @BindingAdapter("bind_background_image")
    fun bindBackgroundImage(view: View, dustState: DustState?) {
        Log.d("dust state", "${ImageUtils.convertStateToImage(dustState ?: DustState.GOOD)}")
        view.setBackgroundResource(ImageUtils.convertStateToImage(dustState ?: DustState.GOOD))
    }
}