package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    private val _addressLine: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = ""
        }
    }
    val addressLine: LiveData<String>
        get() = _addressLine

    private val _fineDustState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }
    val fineDustState get() = _fineDustState

    private val _fineDustValue: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }
    val fineDustValue get() = _fineDustValue

    private val _ultraFineDustState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }
    val ultraFineDustState get() = _ultraFineDustState

    private val _ultraFineDustValue: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }
    val ultraFineDustValue get() = _ultraFineDustValue

    private val _dataTime: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = ""
        }
    }
    val dataTime get() = _dataTime

    fun setAddressLine(doName: String, siName: String) {
        _addressLine.value = "$doName $siName"
    }

    fun setAddressLine(address: String) {
        _addressLine.value = address
    }

    fun setFineDustState(state: Int) {
        _fineDustState.value = state
    }

    fun setUltraFineDustState(state: Int) {
        _ultraFineDustState.value = state
    }

    fun setDataTime(dataTime: String) {
        _dataTime.value = dataTime
    }

    fun setFineDustValue(value: Int) {
        _fineDustValue.value = value
    }

    fun setUltraFineDustValue(value: Int) {
        _ultraFineDustValue.value = value
    }
}