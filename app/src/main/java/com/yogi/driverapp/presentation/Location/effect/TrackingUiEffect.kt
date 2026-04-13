package com.yogi.driverapp.presentation.Location.effect

sealed class TrackingUiEffect {

    data class ShowToast(val message: String) : TrackingUiEffect()
    object NavigateToDetail : TrackingUiEffect()
    object ShowErrorDialog : TrackingUiEffect()

}