package com.yogi.driverapp.domain.usecase

import com.yogi.driverapp.data.model.responce.Photo
import com.yogi.driverapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(): List<Photo> {
        return repository.getPhotos()
    }
}