package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.HomeRepository
import com.ysshin.fine_dust_app.utils.ImageUtils

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    private val _dustState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(3)
        }
    }

    val dustState: LiveData<Int>
        get() = _dustState

    fun setDustState(state: Int) {
        _dustState.value = state
    }

    fun getDustStateResource() = ImageUtils.convertStateToImage(_dustState.value ?: 0)
}