package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.HomeRepository
import java.time.LocalDateTime

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

    private val _maxTemperature: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>().apply {
            value = 0.0
        }
    }
    val maxTemperature get() = _maxTemperature

    private val _minTemperature: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>().apply {
            value = 0.0
        }
    }
    val minTemperature get() = _minTemperature

    private val _loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }
    val loading get() = _loading

    fun setMaxTemperature(temperature: Double) {
        _maxTemperature.value = temperature
    }

    fun setMinTemperature(temperature: Double) {
        _minTemperature.value = temperature
    }

    fun setLoading(isLoading: Boolean) {
        loading.value = isLoading
    }

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

    fun getFineDustData(doName: String, siName: String) =
        repository.getDusts(doName = doName, siName = siName)

    fun getWeatherData(lat: Int, lng: Int) = repository.getWeatherInformation(lat = lat, lng = lng)

    fun needUpdate(): Boolean {
        val dataTime = _dataTime.value ?: return true
        if (dataTime.isEmpty())
            return true

        val split = dataTime.split(":", "-", " ")
        val dataHour = split[3].toInt()

        val curTime = LocalDateTime.now()
        val curHour = curTime.hour

        return kotlin.math.abs(dataHour - curHour) >= 2
    }

}