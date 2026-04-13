package com.yogi.driverapp.data.model.request

data class PostRequestDto(
    val title: String,
    val body: String,
    val userId: Int = 1
)