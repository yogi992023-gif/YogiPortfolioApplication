package com.yogi.interviewproject.data.location

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
import com.google.android.gms.maps.model.LatLng
import com.yogi.interviewproject.data.model.responce.CommonLocation
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationHelper @Inject constructor(@ApplicationContext private val context: Context) {

    private val client = LocationServices.getFusedLocationProviderClient(context)

    fun startLocationUpdates(onUpdate: (CommonLocation) -> Unit) {

        val request = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            3000
        ).build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {

                val loc = result.lastLocation ?: return

                onUpdate(CommonLocation(loc.latitude, loc.longitude))
            }
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        client.requestLocationUpdates(
            request,
            callback,
            Looper.getMainLooper()
        )
    }
}