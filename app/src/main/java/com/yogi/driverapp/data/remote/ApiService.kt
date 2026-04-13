package com.yogi.driverapp.data.remote

import com.yogi.driverapp.data.model.request.PostRequestDto
import com.yogi.driverapp.data.model.responce.Category
import com.yogi.driverapp.data.model.responce.Photo
import com.yogi.driverapp.data.model.responce.PostResponse
import com.yogi.driverapp.domain.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @POST("posts")
    suspend fun createPost(@Body request: PostRequestDto): PostResponse

    @GET("users") // mock API for dropdown
    suspend fun getCategories(): List<Category>

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}
