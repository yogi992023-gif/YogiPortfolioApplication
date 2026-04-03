package com.yogi.interviewproject.domain.usecase

import com.yogi.interviewproject.data.model.responce.CommonLocation
import com.yogi.interviewproject.data.model.responce.DirectionsResponse
import com.yogi.interviewproject.data.model.responce.DriverLocation
import com.yogi.interviewproject.domain.repository.LocationRepositoy
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: LocationRepositoy) {

    suspend operator fun invoke(origin: String, destination: String, key: String): DirectionsResponse {
        return repository.getPathRoute(origin,destination, key)
    }

    suspend fun listenDriverLocation(driverId: String, onUpdate: (CommonLocation) -> Unit){
        repository.listenDriverLocation(driverId, onUpdate)
    }
}