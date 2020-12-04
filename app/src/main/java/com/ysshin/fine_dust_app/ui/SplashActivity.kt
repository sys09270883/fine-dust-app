package com.ysshin.fine_dust_app.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ysshin.fine_dust_app.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private val permissionRequestCode = 100
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (!checkLocationServiceStatus())
            showLocationSettingDialog()
        else
            checkRuntimePermission()
    }

    private fun checkRuntimePermission() {
        val hasFineLocationPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val hasCoarseLocationPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(this@SplashActivity, AuthActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        } else {
            ActivityCompat.requestPermissions(this, requiredPermissions, permissionRequestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionRequestCode && grantResults.size == requiredPermissions.size) {
            var checkResult = true
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }

            if (checkResult) {
                val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        requiredPermissions[0]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        requiredPermissions[1]
                    )
                ) {
                    Toast.makeText(this, "권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "앱 설정에서 권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun checkLocationServiceStatus(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) or
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) or
                locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
    }

    private fun showLocationSettingDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.")
        builder.setCancelable(true)
        builder.setPositiveButton("설정") { _, _ ->
            val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("취소") { dialog, _ ->
            dialog.cancel()
            finish()
        }
        builder.create().show()
    }

}