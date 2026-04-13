package com.yogi.driverapp.domain.repository

import com.yogi.driverapp.data.model.request.PostRequestDto
import com.yogi.driverapp.data.model.responce.Category
import com.yogi.driverapp.data.model.responce.Photo
import com.yogi.driverapp.data.model.responce.PostResponse
import com.yogi.driverapp.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun createPost(request: PostRequestDto): PostResponse
    suspend fun getCategories(): List<Category>
    suspend fun getPhotos(): List<Photo>
}
