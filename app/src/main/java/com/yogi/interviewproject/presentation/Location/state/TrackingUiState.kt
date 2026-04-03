package com.yogi.interviewproject.presentation.Location.state

import com.google.android.gms.maps.model.LatLng
import com.yogi.interviewproject.data.model.responce.CommonLocation

data class TrackingUiState(
    val isLoading: Boolean = false,
    val title: String = "",
    val body: String = "",
    val error: String? = null,
    var userLocation : CommonLocation? = null,
    val driverLocation: CommonLocation? = null) {
}