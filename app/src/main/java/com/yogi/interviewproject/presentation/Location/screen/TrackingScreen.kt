package com.yogi.interviewproject.presentation.Location.screen

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
import com.yogi.interviewproject.presentation.Location.Event.TrackingUiEvent
import com.yogi.interviewproject.presentation.Location.viewModel.UserTrackingViewModel

@Composable
fun TrackingScreen(navController: NavController, viewModel: UserTrackingViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()
    val activity = context as Activity
   // val userLocation = viewModel.userLocation
  //  val driverLocation = viewModel.driverLocation

    val route = viewModel.routePoints

    LaunchedEffect(Unit) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            100
        )
    }

    // Start
    LaunchedEffect(Unit) {

        viewModel.start("user_1", "driver_1")

    }

    val user = state.userLocation
    val driver = state.driverLocation

    if (user == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Getting your location...")
        }
        return
    }

    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(user.lat, user.lng), 14f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraState
    ) {

        // 📍 USER
        Marker(
            state = MarkerState(position = LatLng(user.lat, user.lng)),
            title = "You 📍"
        )

        // 🚗 DRIVER
        driver?.let {
            Marker(
                state = MarkerState(position = LatLng(it.lat, it.lng)),
                title = "Driver 🚗"
            )
        }

        // Optional line
        if (driver != null) {
            Polyline(
                points = listOf(LatLng(user.lat, user.lng), LatLng(driver.lat, driver.lng)),
                width = 6f
            )
        }
    }
}