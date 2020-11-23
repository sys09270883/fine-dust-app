package com.ysshin.fine_dust_app.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "app_pref"
        private const val TOKEN_KEY = "token_key"
    }

    private fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        val editor = getPreferences().edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }
}