package com.yogi.interviewproject.domain.usecase

import com.yogi.interviewproject.data.model.responce.Photo
import com.yogi.interviewproject.domain.repository.PostRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(): List<Photo> {
        return repository.getPhotos()
    }
}