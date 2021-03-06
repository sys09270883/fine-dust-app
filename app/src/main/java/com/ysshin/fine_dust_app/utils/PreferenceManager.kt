package com.ysshin.fine_dust_app.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "app_pref"
        private const val TOKEN_KEY = "token_key"
        private const val CLEARED_TOKEN = "none"
        private const val ADDRESS_LINE = "address_line"
        private const val DEFAULT = ""
        private const val DEFAULT_INT = 0
        private const val DEFAULT_FLOAT = 0.0f
        private const val DO_NAME = "do_name"
        private const val SI_NAME = "si_name"
        private const val DATA_TIME = "data_time"
        private const val CITY_NAME = "city_name"
        private const val SO2_VALUE = "so2_value"
        private const val CO_VALUE = "co_value"
        private const val O3_VALUE = "o3_value"
        private const val NO2_VALUE = "no2_value"
        private const val PM10_VALUE = "pm10_value"
        private const val PM25_VALUE = "pm25_value"
        private const val MAX_TEMPERATURE = "max_temperature"
        private const val MIN_TEMPERATURE = "min_temperature"
        private const val MORNING_SKY_STATE = "morning_sky"
        private const val AFTERNOON_SKY_STATE = "afternoon_sky"
        private const val EVENING_SKY_STATE = "evening_sky"
    }

    private fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun clearToken() {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(TOKEN_KEY, CLEARED_TOKEN)
        editor.apply()
    }

    fun getToken(): String? {
        val pref = getPreferences()
        return pref.getString(TOKEN_KEY, null)
    }

    fun saveAddressLine(addressLine: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(ADDRESS_LINE, addressLine)
        editor.apply()
    }

    fun getAddressLine(): String {
        val pref = getPreferences()
        return pref.getString(ADDRESS_LINE, DEFAULT)!!
    }

    fun saveDoName(doName: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(DO_NAME, doName)
        editor.apply()
    }

    fun getDoName(): String {
        val pref = getPreferences()
        return pref.getString(DO_NAME, DEFAULT)!!
    }

    fun saveSiName(siName: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(SI_NAME, siName)
        editor.apply()
    }

    fun getSiName(): String {
        val pref = getPreferences()
        return pref.getString(SI_NAME, DEFAULT)!!
    }

    fun saveDataTime(dataTime: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(DATA_TIME, dataTime)
        editor.apply()
    }

    fun getDataTime(): String {
        val pref = getPreferences()
        return pref.getString(DATA_TIME, DEFAULT)!!
    }

    fun saveCityName(cityName: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(CITY_NAME, cityName)
        editor.apply()
    }

    fun getCityName(): String {
        val pref = getPreferences()
        return pref.getString(CITY_NAME, DEFAULT)!!
    }

    fun saveSo2Value(so2Value: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(SO2_VALUE, so2Value)
        editor.apply()
    }

    fun getSo2Value(): String {
        val pref = getPreferences()
        return pref.getString(SO2_VALUE, DEFAULT)!!
    }

    fun saveCoValue(coValue: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(CO_VALUE, coValue)
        editor.apply()
    }

    fun getCoValue(): String {
        val pref = getPreferences()
        return pref.getString(CO_VALUE, DEFAULT)!!
    }

    fun saveO3Value(o3Value: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(O3_VALUE, o3Value)
        editor.apply()
    }

    fun getO3Value(): String {
        val pref = getPreferences()
        return pref.getString(O3_VALUE, DEFAULT)!!
    }

    fun saveNo2Value(no2Value: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(NO2_VALUE, no2Value)
        editor.apply()
    }

    fun getNo2Value(): String {
        val pref = getPreferences()
        return pref.getString(NO2_VALUE, DEFAULT)!!
    }

    fun savePm10Value(pm10Value: Int) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putInt(PM10_VALUE, pm10Value)
        editor.apply()
    }

    fun getPm10Value(): Int {
        val pref = getPreferences()
        return pref.getInt(PM10_VALUE, DEFAULT_INT)
    }

    fun savePm25Value(pm25Value: Int) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putInt(PM25_VALUE, pm25Value)
        editor.apply()
    }

    fun getPm25Value(): Int {
        val pref = getPreferences()
        return pref.getInt(PM25_VALUE, DEFAULT_INT)
    }

    fun saveMaxTemperature(maxTemperature: Double) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putFloat(MAX_TEMPERATURE, maxTemperature.toFloat())
        editor.apply()
    }

    fun getMaxTemperature(): Double {
        val pref = getPreferences()
        return pref.getFloat(MAX_TEMPERATURE, DEFAULT_FLOAT).toDouble()
    }

    fun saveMinTemperature(minTemperature: Double) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putFloat(MIN_TEMPERATURE, minTemperature.toFloat())
        editor.apply()
    }

    fun getMinTemperature(): Double {
        val pref = getPreferences()
        return pref.getFloat(MIN_TEMPERATURE, DEFAULT_FLOAT).toDouble()
    }

    fun setMorningSkyState(value: Int) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putInt(MORNING_SKY_STATE, value)
        editor.apply()
    }

    fun getMorningSkyState(): Int {
        val pref = getPreferences()
        return pref.getInt(MORNING_SKY_STATE, DEFAULT_INT)
    }

    fun setAfternoonSkyState(value: Int) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putInt(AFTERNOON_SKY_STATE, value)
        editor.apply()
    }

    fun getAfternoonSkyState(): Int {
        val pref = getPreferences()
        return pref.getInt(AFTERNOON_SKY_STATE, DEFAULT_INT)
    }

    fun setEveningSkyState(value: Int) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putInt(EVENING_SKY_STATE, value)
        editor.apply()
    }

    fun getEveningSkyState(): Int {
        val pref = getPreferences()
        return pref.getInt(EVENING_SKY_STATE, DEFAULT_INT)
    }

}