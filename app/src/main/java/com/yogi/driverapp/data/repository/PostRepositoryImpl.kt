package com.yogi.driverapp.data.repository

import com.yogi.driverapp.data.model.request.PostRequestDto
import com.yogi.driverapp.data.model.responce.Category
import com.yogi.driverapp.data.model.responce.Photo
import com.yogi.driverapp.data.model.responce.PostResponse
import com.yogi.driverapp.data.remote.ApiService
import com.yogi.driverapp.domain.model.Post
import com.yogi.driverapp.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val api: ApiService) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        return api.getPosts()
    }

    override suspend fun createPost(request: PostRequestDto): PostResponse {
        return api.createPost(request)
    }

    override suspend fun getCategories(): List<Category> {
        return api.getCategories()
    }

    override suspend fun getPhotos(): List<Photo> {
        return api.getPhotos()
    }


}
