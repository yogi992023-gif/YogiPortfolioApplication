package com.yogi.driverapp.domain.usecase

import com.yogi.driverapp.data.model.request.PostRequestDto
import com.yogi.driverapp.data.model.responce.PostResponse
import com.yogi.driverapp.domain.model.Post
import com.yogi.driverapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(): List<Post> {
        return repository.getPosts()
    }

    suspend operator fun invoke(request: PostRequestDto): PostResponse {
        return repository.createPost(request)
    }
}
