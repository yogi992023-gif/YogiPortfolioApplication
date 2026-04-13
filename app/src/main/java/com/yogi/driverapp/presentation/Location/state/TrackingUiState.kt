package com.yogi.driverapp.presentation.Location.state

import com.google.android.gms.maps.model.LatLng

data class TrackingUiState(
    val isLoading: Boolean = false,
    val title: String = "",
    val body: String = "",
    val error: String? = null,
    var userLocation : LatLng? = null,
    val driverLocation: LatLng? = null) {
}