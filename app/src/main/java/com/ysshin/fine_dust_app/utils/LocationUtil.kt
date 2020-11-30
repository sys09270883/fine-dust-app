package com.ysshin.fine_dust_app.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import java.util.*

class LocationUtil private constructor(val context: Context) {
    companion object {
        private var instance: LocationUtil? = null
        private const val MAX_RESULT = 7

        fun getInstance(context: Context): LocationUtil =
            instance ?: synchronized(this) {
                instance ?: LocationUtil(context).also { instance = it }
            }
    }

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            val location = locationManager.getLastKnownLocation(provider)
            location?.apply {
                if (bestLocation == null || location.accuracy < bestLocation!!.accuracy)
                    bestLocation = location
            }
        }
        return bestLocation
    }

    private fun getAddress(): Address? {
        val location = getLocation() ?: return null
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, MAX_RESULT)
        if (addresses == null || addresses.size == 0)
            return null
        return addresses.first()
    }

    fun getCurrentLocationData(): List<String>? {
        val address = getAddress() ?: return null
        val splitAddress = address.getAddressLine(0).split(" ")
        return listOf(splitAddress[1], splitAddress[2])
    }
}