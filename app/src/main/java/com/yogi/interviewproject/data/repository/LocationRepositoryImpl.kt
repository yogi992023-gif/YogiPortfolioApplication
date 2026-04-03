package com.yogi.interviewproject.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.type.LatLng
import com.yogi.interviewproject.data.datasource.FirebaseDataSourceTracking
import com.yogi.interviewproject.data.location.LocationHelper
import com.yogi.interviewproject.data.model.responce.CommonLocation
import com.yogi.interviewproject.data.model.responce.DirectionsResponse
import com.yogi.interviewproject.data.model.responce.DriverLocation
import com.yogi.interviewproject.data.remote.DirectionsApi
import com.yogi.interviewproject.domain.repository.LocationRepositoy
import javax.inject.Inject


class LocationRepositoryImpl  @Inject constructor(private val api: DirectionsApi,
                                                  private val locationDS: LocationHelper,
                                                  private val dataSource: FirebaseDataSourceTracking) : LocationRepositoy {

    override fun listenDriverLocation(driverId: String, onUpdate: (CommonLocation) -> Unit) {
        dataSource.listenDriverLocation(driverId, onUpdate)
    }


    override fun userTracking(userId: String, onUpdate: (CommonLocation) -> Unit) {
        locationDS.startLocationUpdates { loc ->

            onUpdate(loc) // UI update
            dataSource.sendUserLocation(userId, loc) // Firebase
        }
    }

    override suspend fun getPathRoute(origin: String,destination: String, key: String): DirectionsResponse {
        return api.getRoute(origin,destination,key)
    }


}