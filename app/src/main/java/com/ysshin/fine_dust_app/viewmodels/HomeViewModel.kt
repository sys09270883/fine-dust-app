package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    private val _dustState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(2)
        }
    }
    val dustState: LiveData<Int>
        get() = _dustState

    private val _addressLine: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = ""
        }
    }
    val addressLine: LiveData<String>
        get() = _addressLine

    fun setDustState(state: Int) {
        _dustState.value = state
    }

    fun setAddressLine(doName: String, siName: String) {
        _addressLine.value = "$doName $siName"
    }

    fun setAddressLine(address: String) {
        _addressLine.value = address
    }
}