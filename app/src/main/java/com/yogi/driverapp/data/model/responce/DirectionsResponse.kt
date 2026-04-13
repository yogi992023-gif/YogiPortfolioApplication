package com.yogi.driverapp.data.model.responce

data class DirectionsResponse(
    val routes: List<Route>
)

data class Route(
    val overview_polyline: PolylineData
)

data class PolylineData(
    val points: String
)