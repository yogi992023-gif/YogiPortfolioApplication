package com.yogi.interviewproject.data.remote

import com.yogi.interviewproject.data.model.request.PostRequestDto
import com.yogi.interviewproject.data.model.responce.Category
import com.yogi.interviewproject.data.model.responce.Photo
import com.yogi.interviewproject.data.model.responce.PostResponse
import com.yogi.interviewproject.data.model.responce.ProductResponce
import com.yogi.interviewproject.domain.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

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
