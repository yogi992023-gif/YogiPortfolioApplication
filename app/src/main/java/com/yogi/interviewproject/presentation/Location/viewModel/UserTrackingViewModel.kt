package com.yogi.interviewproject.presentation.Location.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.yogi.interviewproject.presentation.Location.Event.TrackingUiEvent
import com.yogi.interviewproject.data.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserTrackingViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel() {

    fun onEvent(event: TrackingUiEvent) {
        when(event){
            TrackingUiEvent.LoadDriverLocation -> {
                startTracking("driver_1")
            }
            is TrackingUiEvent.OnBodyChange -> TODO()
            is TrackingUiEvent.OnItemClick -> TODO()
            is TrackingUiEvent.OnSubmit -> TODO()
            is TrackingUiEvent.OnTitleChange -> TODO()
            TrackingUiEvent.Submit -> TODO()
        }
    }

    //  saravanampatty   Lat Long   (11.078556, 77.003513)
    val userLocation = LatLng(11.082612, 76.967382)
    var driverLocation by mutableStateOf(
        LatLng(11.078556, 77.003513)
    )
        private set

    init {
     //   moveDriver()
    }

    private fun moveDriver() {
        viewModelScope.launch {
            while (true) {

                delay(1000)

                // Move towards user
                val newLat = driverLocation.latitude +
                        (userLocation.latitude - driverLocation.latitude) * 0.05

                val newLng = driverLocation.longitude +
                        (userLocation.longitude - driverLocation.longitude) * 0.05

                driverLocation = LatLng(newLat, newLng)
            }
        }
    }

    fun startTracking(driverId: String) {

        repo.listenDriverLocation(driverId) {

            driverLocation = LatLng(it.lat, it.lng)
        }
    }
}