package com.yogi.interviewproject.presentation.Location.Event

import com.yogi.interviewproject.domain.model.Post

sealed class TrackingUiEvent {

    object LoadDriverLocation : TrackingUiEvent()
    data class OnItemClick(val id: Int) : TrackingUiEvent()
    data class OnSubmit(val data: Post, val body: String) : TrackingUiEvent()
    data class OnTitleChange(val value: String) : TrackingUiEvent()
    data class OnBodyChange(val value: String) : TrackingUiEvent()
    object Submit : TrackingUiEvent()

}