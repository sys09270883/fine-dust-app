package com.ysshin.fine_dust_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ysshin.fine_dust_app.R
import com.ysshin.fine_dust_app.databinding.ActivityHomeBinding
import com.ysshin.fine_dust_app.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}