package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val dustState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }

    fun getDustState() = dustState.value ?: 0

}