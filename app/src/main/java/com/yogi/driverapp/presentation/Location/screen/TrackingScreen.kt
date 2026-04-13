package com.yogi.driverapp.presentation.Location.screen

import android.Manifest
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.yogi.driverapp.presentation.Location.Event.TrackingUiEvent
import com.yogi.driverapp.presentation.Location.viewModel.DriverViewModel

@Composable
fun TrackingScreen(navController: NavController, viewModel: DriverViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()
    val route = viewModel.routePoints
    val activity = context as Activity

    // Permission
    LaunchedEffect(Unit) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            100
        )
    }

    // Start tracking
    LaunchedEffect(Unit) {
        viewModel.start("driver_1", "user_1")
    }

    val driver = state.driverLocation
    val user = state.userLocation

    if (driver == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Getting location...")
        }
        return
    }

    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(driver, 14f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraState
    ) {

        // 🚗 Driver Marker
        Marker(
            state = MarkerState(position = driver),
            title = "Driver 🚗"
        )

        // 📍 User Marker
        user?.let {
            Marker(
                state = MarkerState(position = it),
                title = "User 📍"
            )
        }

        // 🔵 Optional line between both
        if (user != null) {
            Polyline(
                points = listOf(driver, user),
                width = 6f
            )
        }
    }
}