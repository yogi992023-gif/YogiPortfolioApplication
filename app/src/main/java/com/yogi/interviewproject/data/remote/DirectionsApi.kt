package com.yogi.interviewproject.data.remote

import com.yogi.interviewproject.data.model.responce.DirectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApi {

    @GET("maps/api/directions/json")
    suspend fun getRoute(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") key: String
    ): DirectionsResponse
}