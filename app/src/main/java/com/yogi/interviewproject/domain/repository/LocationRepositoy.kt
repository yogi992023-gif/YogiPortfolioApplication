package com.yogi.interviewproject.domain.repository

import com.google.type.LatLng
import com.yogi.interviewproject.data.model.responce.CommonLocation
import com.yogi.interviewproject.data.model.responce.DirectionsResponse
import com.yogi.interviewproject.data.model.responce.DriverLocation

interface LocationRepositoy {
    suspend fun getPathRoute(origin: String,destination: String, key: String) : DirectionsResponse

    fun listenDriverLocation(driverId: String, onUpdate: (CommonLocation) -> Unit)

    fun userTracking(userId: String, onUpdate: (CommonLocation) -> Unit)



}