package com.ysshin.fine_dust_app.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ysshin.fine_dust_app.data.HomeRepository
import com.ysshin.fine_dust_app.utils.AddressConverter
import com.ysshin.fine_dust_app.utils.FineDustConverter
import com.ysshin.fine_dust_app.utils.LocationUtil
import com.ysshin.fine_dust_app.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeViewModel(
    private val repository: HomeRepository,
    private val preferenceManager: PreferenceManager,
    private val locationUtil: LocationUtil
) : ViewModel() {
    private val _addressLine: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val addressLine: LiveData<String>
        get() = _addressLine

    private val _fineDustState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }
    val fineDustState get() = _fineDustState

    private val _morningSkyState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }
    val morningSkyState get() = _morningSkyState

    private val _afternoonSkyState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }
    val afternoonSkyState get() = _afternoonSkyState

    private val _eveningSkyState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }
    val eveningSkyState get() = _eveningSkyState

    private val _fineDustValue: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }
    val fineDustValue get() = _fineDustValue

    private val _ultraFineDustState: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }
    val ultraFineDustState get() = _ultraFineDustState

    private val _ultraFineDustValue: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }
    val ultraFineDustValue get() = _ultraFineDustValue

    private val _dataTime: MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            postValue("")
        }
    }
    val dataTime get() = _dataTime

    private val _maxTemperature: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>().apply {
            postValue(0.0)
        }
    }
    val maxTemperature get() = _maxTemperature

    private val _minTemperature: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>().apply {
            postValue(0.0)
        }
    }
    val minTemperature get() = _minTemperature

    val loading: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0b11)
        }
    }

    private fun setMaxTemperature(temperature: Double) = _maxTemperature.postValue(temperature)

    private fun setMinTemperature(temperature: Double) = _minTemperature.postValue(temperature)

    private fun setLoading(loadingBit: Int) {
        loading.postValue(
            when (loadingBit) {
                0b00 -> 0b00
                else -> loading.value?.or(loadingBit)
            }
        )
    }

    private fun setAddressLine(address: String) = _addressLine.postValue(address)

    private fun setFineDustState(state: Int) = _fineDustState.postValue(state)

    private fun setUltraFineDustState(state: Int) = _ultraFineDustState.postValue(state)

    private fun setDataTime(dataTime: String) = _dataTime.postValue(dataTime)

    private fun setFineDustValue(value: Int) = _fineDustValue.postValue(value)

    private fun setUltraFineDustValue(value: Int) = _ultraFineDustValue.postValue(value)

    private fun setAllFineDustInfo(pm10Value: Int, pm25Value: Int) {
        val fineDustState =
            FineDustConverter.convertToFineDustState(pm10Value)
        val ultraFineDustState =
            FineDustConverter.convertToUltraFineDustState(pm25Value)
        setFineDustValue(pm10Value)
        setUltraFineDustValue(pm25Value)
        setFineDustState(fineDustState)
        setUltraFineDustState(ultraFineDustState)
    }

    fun fetchAllInformation() {
        setLoading(0b00)
        fetchFineDustData()
        fetchWeatherData()
    }

    private fun fetchFineDustData() {
        val doName = preferenceManager.getDoName()
        val siName = preferenceManager.getSiName()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dustResponse = repository.getDusts(doName = doName, siName = siName)
                val dusts = dustResponse.dusts
                for (dust in dusts) {
                    if (preferenceManager.getSiName() == dust.cityName) {
                        preferenceManager.apply {
                            saveDataTime(dust.dataTime)
                            saveCityName(dust.cityName)
                            savePm10Value(dust.pm10Value.toInt())
                            savePm25Value(dust.pm25Value.toInt())
                        }
                        setAllFineDustInfo(dust.pm10Value.toInt(), dust.pm25Value.toInt())
                        setDataTime(dust.dataTime)
                        break
                    }
                }
            } catch (e: Exception) {
                Log.e("yoonseop", "${e.message}")
            } finally {
                setLoading(0b10)
            }
        }
    }

    private fun fetchWeatherData() {
        val location = locationUtil.getLocation() ?: return
        val lat = location.latitude.toInt()
        val lng = location.longitude.toInt()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weatherResponse = repository.getWeatherInformation(lat = lat, lng = lng)
                val skyList = weatherResponse.skyList
                Log.d("yoonseop", "skyList: $skyList")
                for (sky in skyList) {
                    sky.apply {
                        when (hour) {
                            600 -> {
                                setMorningSkyState(value)
                                preferenceManager.setMorningSkyState(value)
                            }
                            1200 -> {
                                setAfternoonSkyState(value)
                                preferenceManager.setAfternoonSkyState(value)
                            }
                            1800 -> {
                                setEveningSkyState(value)
                                preferenceManager.setEveningSkyState(value)
                            }
                        }
                    }
                }
                val maxTemperature = weatherResponse.maxTemperature
                val minTemperature = weatherResponse.minTemperature
                preferenceManager.saveMaxTemperature(maxTemperature)
                preferenceManager.saveMinTemperature(minTemperature)
                setMaxTemperature(maxTemperature)
                setMinTemperature(minTemperature)
            } catch (e: Exception) {
                Log.e("yoonseop", "${e.message}")
            } finally {
                setLoading(0b01)
            }
        }
    }

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

    private fun setMorningSkyState(state: Int) = _morningSkyState.postValue(state)

    private fun setAfternoonSkyState(state: Int) = _afternoonSkyState.postValue(state)

    private fun setEveningSkyState(state: Int) = _eveningSkyState.postValue(state)

    fun initLocation() {
        setLoading(0b00)
        val locationData = locationUtil.getCurrentLocationData()
        val doName = AddressConverter.convert("${locationData?.first()}")
        val siName = "${locationData?.last()}"
        val address = "$doName $siName"
        updateIfAddressNotSame(address, doName, siName)
        initViewModel()
        setLoading(0b11)
    }

    private fun updateIfAddressNotSame(address: String, doName: String, siName: String): Boolean {
        if (preferenceManager.getAddressLine() != address) {
            preferenceManager.saveAddressLine(address)
            preferenceManager.saveDoName(doName)
            preferenceManager.saveSiName(siName)
            return true
        }
        return false
    }

    private fun initViewModel() {
        preferenceManager.let {
            setDataTime(it.getDataTime())
            setAddressLine(it.getAddressLine())
            setAllFineDustInfo(
                it.getPm10Value(),
                it.getPm25Value()
            )
            setMorningSkyState(it.getMorningSkyState())
            setAfternoonSkyState(it.getAfternoonSkyState())
            setEveningSkyState(it.getEveningSkyState())
            setMaxTemperature(it.getMaxTemperature())
            setMinTemperature(it.getMinTemperature())
        }
    }

}