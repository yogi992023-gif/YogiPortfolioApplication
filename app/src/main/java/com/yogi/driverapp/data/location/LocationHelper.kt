package com.yogi.driverapp.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.yogi.driverapp.data.model.responce.DriverLocation
import com.yogi.driverapp.domain.model.CommonLocation
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private val client = LocationServices.getFusedLocationProviderClient(context)

    fun startLocationUpdates(onUpdate: (CommonLocation) -> Unit) {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return // ❌ stop if permission not granted
        }

        val request = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            3000 // every 3 seconds
        ).build()

        val callback = object : LocationCallback() {

            override fun onLocationResult(result: LocationResult) {

                val location = result.lastLocation ?: return

                onUpdate(
                    CommonLocation(
                        lat = location.latitude,
                        lng = location.longitude
                    )
                )
            }
        }

        client.requestLocationUpdates(
            request,
            callback,
            Looper.getMainLooper()
        )
    }
}