package com.yogi.interviewproject.presentation.Location.state

data class TrackingUiState(
    val isLoading: Boolean = false,
    val title: String = "",
    val body: String = "",
    val error: String? = null) {
}