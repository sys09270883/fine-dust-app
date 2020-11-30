package com.ysshin.fine_dust_app.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "app_pref"
        private const val TOKEN_KEY = "token_key"
        private const val CLEARED_TOKEN = "none"
        private const val ADDRESS_LINE = "address_line"
        private const val DEFAULT_ADDRESS_LINE = "none"
        private const val DO_NAME = "do_name"
        private const val DEFAULT_DO_NAME = "none"
        private const val SI_NAME = "si_name"
        private const val DEFAULT_SI_NAME = "none"
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
        return pref.getString(ADDRESS_LINE, DEFAULT_ADDRESS_LINE)!!
    }

    fun saveDoName(doName: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(DO_NAME, doName)
        editor.apply()
    }

    fun getDoName(): String {
        val pref = getPreferences()
        return pref.getString(DO_NAME, DEFAULT_DO_NAME)!!
    }

    fun saveSiName(siName: String) {
        val pref = getPreferences()
        val editor = pref.edit()
        editor.putString(SI_NAME, siName)
        editor.apply()
    }

    fun getSiName(): String {
        val pref = getPreferences()
        return pref.getString(SI_NAME, DEFAULT_SI_NAME)!!
    }
}