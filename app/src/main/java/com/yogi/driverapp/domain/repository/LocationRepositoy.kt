package com.yogi.driverapp.domain.repository

import com.yogi.driverapp.data.model.responce.DirectionsResponse
import com.yogi.driverapp.data.model.responce.DriverLocation
import com.yogi.driverapp.domain.model.CommonLocation

interface LocationRepositoy {
    suspend fun getPathRoute(origin: String,destination: String, key: String) : DirectionsResponse

    fun startTracking(driverId: String, onUpdate: (CommonLocation) -> Unit)

    fun listenUser(userId: String, onUpdate: (CommonLocation) -> Unit)

}