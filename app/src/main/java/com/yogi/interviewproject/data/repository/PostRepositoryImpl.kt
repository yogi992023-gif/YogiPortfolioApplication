package com.yogi.interviewproject.data.repository

import com.yogi.interviewproject.data.model.request.PostRequestDto
import com.yogi.interviewproject.data.model.responce.Category
import com.yogi.interviewproject.data.model.responce.Photo
import com.yogi.interviewproject.data.model.responce.PostResponse
import com.yogi.interviewproject.data.model.responce.ProductResponce
import com.yogi.interviewproject.data.remote.ApiService
import com.yogi.interviewproject.domain.model.Post
import com.yogi.interviewproject.domain.repository.PostRepository
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
