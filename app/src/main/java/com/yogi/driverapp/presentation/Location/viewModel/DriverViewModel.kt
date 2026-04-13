package com.yogi.driverapp.presentation.Location.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.yogi.driverapp.data.datasource.DriverFirebaseDataSource
import com.yogi.driverapp.data.location.LocationHelper
import com.yogi.driverapp.presentation.Location.Event.TrackingUiEvent
import com.yogi.driverapp.data.repository.LocationRepositoryImpl
import com.yogi.driverapp.domain.repository.LocationRepositoy
import com.yogi.driverapp.domain.usecase.GetLocationUseCase
import com.yogi.driverapp.presentation.Location.effect.TrackingUiEffect
import com.yogi.driverapp.presentation.Location.state.TrackingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(
    private val getRouteUseCase: GetLocationUseCase,
    private val repo: LocationRepositoryImpl,
    private val repoLocation: LocationRepositoy, @ApplicationContext context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(TrackingUiState())
    val uiState: StateFlow<TrackingUiState> = _uiState

    private val _effect = Channel<TrackingUiEffect>()
    val effect = _effect.receiveAsFlow()
    var routePoints by mutableStateOf<List<LatLng>>(emptyList())
        private set

    fun onEvent(event: TrackingUiEvent) {
        when(event){
            TrackingUiEvent.LoadDriverLocation -> {
            }
            is TrackingUiEvent.OnBodyChange -> TODO()
            is TrackingUiEvent.OnItemClick -> TODO()
            is TrackingUiEvent.OnSubmit -> TODO()
            is TrackingUiEvent.OnTitleChange -> TODO()
            TrackingUiEvent.Submit -> TODO()
            is TrackingUiEvent.StartTracking -> {
            }
        }
    }

    fun start(driverId: String, userId: String) {

        // Driver GPS → Firebase
        repo.startTracking(driverId) { driver ->

            _uiState.value = _uiState.value.copy(
                driverLocation = LatLng(driver.lat, driver.lng),
                isLoading = false
            )
        }

        // Listen USER
        repo.listenUser(userId) { user ->

            _uiState.value = _uiState.value.copy(
                userLocation = LatLng(user.lat, user.lng)
            )
        }
    }

    fun startTracking(driverId: String) {

        repoLocation.startTracking(driverId) { location ->

            _uiState.value = _uiState.value.copy(
                driverLocation = LatLng(location.lat, location.lng),
                isLoading = false
            )
        }
    }

   /* private fun moveDriver() {
        viewModelScope.launch {
            while (true) {

                delay(1000)

                // Move towards user
                val newLat = driverLocation.latitude +
                        ((_userLocation.value.latitude?.minus(driverLocation.latitude))?.times(0.05) ?: 0.0)

                val newLng = driverLocation.longitude +
                        ((_userLocation.value.longitude?.minus(driverLocation.longitude))?.times(0.05) ?: 0.0)

                driverLocation = LatLng(newLat, newLng)
            }
        }
    }*/

   /* fun getDriverLocation() {
        locationHelper.getCurrentLocation { it ->
            Log.e("location tracking"," ${it.latitude} ${it.longitude}")

            val userLocation = UserLocation(
                lat = it.latitude,
                lng = it.longitude
            )

            _uiState.value = _uiState.value.copy(
                userLocation = userLocation
            )
        }
    }*/


    fun fetchRoute(start: LatLng, end: LatLng) {

        viewModelScope.launch {

            val origin = "${start.latitude},${start.longitude}"
            val destination = "${end.latitude},${end.longitude}"

            val response = getRouteUseCase.invoke(origin, destination, "AIzaSyBW9vVEr1l2LkSzijd4tQHCGMzKEV_sB0c")

            val encoded = response.routes.first().overview_polyline.points

            routePoints = decodePolyline(encoded)
        }
    }

    fun decodePolyline(encoded: String): List<LatLng> {

        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {

            var b: Int
            var shift = 0
            var result = 0

            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)

            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0

            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)

            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            poly.add(LatLng(lat / 1E5, lng / 1E5))
        }

        return poly
    }
}