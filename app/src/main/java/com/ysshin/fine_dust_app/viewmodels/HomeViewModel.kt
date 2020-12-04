package com.ysshin.fine_dust_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysshin.fine_dust_app.data.HomeRepository
import com.ysshin.fine_dust_app.utils.FineDustConverter
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

    private val _morningSkyState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }
    val morningSkyState get() = _morningSkyState

    private val _afternoonSkyState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }
    val afternoonSkyState get() = _afternoonSkyState

    private val _eveningSkyState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }
    val eveningSkyState get() = _eveningSkyState

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

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }

    fun setMaxTemperature(temperature: Double) {
        _maxTemperature.value = temperature
    }

    fun setMinTemperature(temperature: Double) {
        _minTemperature.value = temperature
    }

    fun setLoading(isLoading: Boolean) {
        loading.value = isLoading
    }

    fun setAddressLine(address: String) {
        _addressLine.value = address
    }

    private fun setFineDustState(state: Int) {
        _fineDustState.value = state
    }

    private fun setUltraFineDustState(state: Int) {
        _ultraFineDustState.value = state
    }

    fun setDataTime(dataTime: String) {
        _dataTime.value = dataTime
    }

    private fun setFineDustValue(value: Int) {
        _fineDustValue.value = value
    }

    private fun setUltraFineDustValue(value: Int) {
        _ultraFineDustValue.value = value
    }

    fun setAllFineDustInfo(pm10Value: Int, pm25Value: Int) {
        val fineDustState =
            FineDustConverter.convertToFineDustState(pm10Value)
        val ultraFineDustState =
            FineDustConverter.convertToUltraFineDustState(pm25Value)
        setFineDustValue(pm10Value)
        setUltraFineDustValue(pm25Value)
        setFineDustState(fineDustState)
        setUltraFineDustState(ultraFineDustState)
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

    fun setMorningSkyState(state: Int) {
        _morningSkyState.value = state
    }

    fun setAfternoonSkyState(state: Int) {
        _morningSkyState.value = state
    }

    fun setEveningSkyState(state: Int) {
        _morningSkyState.value = state
    }

}