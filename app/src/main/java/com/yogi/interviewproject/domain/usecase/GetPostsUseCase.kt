package com.yogi.interviewproject.domain.usecase

import com.yogi.interviewproject.data.model.request.PostRequestDto
import com.yogi.interviewproject.data.model.responce.Category
import com.yogi.interviewproject.data.model.responce.PostResponse
import com.yogi.interviewproject.domain.model.Post
import com.yogi.interviewproject.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(): List<Post> {
        return repository.getPosts()
    }

    suspend operator fun invoke(request: PostRequestDto): PostResponse {
        return repository.createPost(request)
    }
}
