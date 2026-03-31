package com.yogi.interviewproject.presentation.Location.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.yogi.interviewproject.presentation.Location.Event.TrackingUiEvent
import com.yogi.interviewproject.presentation.Location.viewModel.UserTrackingViewModel

@Composable
fun TrackingScreen(navController: NavController, viewModel: UserTrackingViewModel = hiltViewModel()) {

    val userLocation = viewModel.userLocation
    val driverLocation = viewModel.driverLocation

    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driverLocation, 14f)
    }

    // Camera follow driver
    LaunchedEffect(driverLocation) {
        cameraState.animate(
            CameraUpdateFactory.newLatLng(driverLocation)
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraState
    ) {

        // 📍 User Marker
        Marker(
            state = MarkerState(position = userLocation),
            title = "You 📍"
        )

        // 🚗 Driver Marker
        Marker(
            state = MarkerState(position = driverLocation),
            title = "Driver 🚗"
        )

        // 🔵 Shortest Path Line
        Polyline(
            points = listOf(userLocation, driverLocation),
            width = 8f
        )
    }
}