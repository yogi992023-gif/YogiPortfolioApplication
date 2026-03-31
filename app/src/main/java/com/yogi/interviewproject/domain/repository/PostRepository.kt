package com.yogi.interviewproject.domain.repository

import com.yogi.interviewproject.data.model.request.PostRequestDto
import com.yogi.interviewproject.data.model.responce.Category
import com.yogi.interviewproject.data.model.responce.Photo
import com.yogi.interviewproject.data.model.responce.PostResponse
import com.yogi.interviewproject.data.model.responce.ProductResponce
import com.yogi.interviewproject.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun createPost(request: PostRequestDto): PostResponse
    suspend fun getCategories(): List<Category>
    suspend fun getPhotos(): List<Photo>
}
