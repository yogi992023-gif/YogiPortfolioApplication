package com.yogi.driverapp.presentation.Location.Event

import com.yogi.driverapp.domain.model.Post

sealed class TrackingUiEvent {

    object LoadDriverLocation : TrackingUiEvent()
    data class OnItemClick(val id: Int) : TrackingUiEvent()
    data class OnSubmit(val data: Post, val body: String) : TrackingUiEvent()
    data class OnTitleChange(val value: String) : TrackingUiEvent()
    data class OnBodyChange(val value: String) : TrackingUiEvent()
    object Submit : TrackingUiEvent()
    data class StartTracking(val driverId: String) : TrackingUiEvent()

}