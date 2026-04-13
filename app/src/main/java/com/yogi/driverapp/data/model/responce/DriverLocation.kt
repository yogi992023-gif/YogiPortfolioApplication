package com.yogi.driverapp.data.model.responce

data class DriverLocation(
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis()
)