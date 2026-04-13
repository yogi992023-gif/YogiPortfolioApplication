package com.yogi.driverapp.domain.usecase

import com.yogi.driverapp.data.model.responce.DirectionsResponse
import com.yogi.driverapp.data.model.responce.DriverLocation
import com.yogi.driverapp.domain.model.CommonLocation
import com.yogi.driverapp.domain.repository.LocationRepositoy
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: LocationRepositoy) {

    suspend operator fun invoke(origin: String, destination: String, key: String): DirectionsResponse {
        return repository.getPathRoute(origin,destination, key)
    }

    suspend fun listenDriverLocation(driverId: String, onUpdate: (CommonLocation) -> Unit){
        repository.startTracking(driverId, onUpdate)
    }
}