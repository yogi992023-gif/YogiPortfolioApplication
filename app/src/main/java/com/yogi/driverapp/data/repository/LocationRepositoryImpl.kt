package com.yogi.driverapp.data.repository

import com.yogi.driverapp.data.datasource.DriverFirebaseDataSource
import com.yogi.driverapp.data.location.LocationHelper
import com.yogi.driverapp.data.model.responce.DirectionsResponse
import com.yogi.driverapp.data.model.responce.DriverLocation
import com.yogi.driverapp.data.remote.DirectionsApi
import com.yogi.driverapp.domain.model.CommonLocation
import com.yogi.driverapp.domain.repository.LocationRepositoy
import javax.inject.Inject


class LocationRepositoryImpl  @Inject constructor(private val api: DirectionsApi, private val locationHelper: LocationHelper,
                                                  private val dataSource: DriverFirebaseDataSource
) : LocationRepositoy {

    override suspend fun getPathRoute(origin: String,destination: String, key: String): DirectionsResponse {
        return api.getRoute(origin,destination,key)
    }

    override fun startTracking(driverId: String, onUpdate: (CommonLocation) -> Unit) {
        locationHelper.startLocationUpdates { location ->

            onUpdate(location) // UI update
            dataSource.sendDriverLocation(driverId, location) // Firebase update
        }
    }

    override fun listenUser(userId: String, onUpdate: (CommonLocation) -> Unit) {
        dataSource.listenUserLocation(userId, onUpdate)
    }

}