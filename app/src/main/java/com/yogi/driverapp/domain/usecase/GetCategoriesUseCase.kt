package com.yogi.driverapp.domain.usecase

import com.yogi.driverapp.data.model.responce.Category
import com.yogi.driverapp.domain.repository.PostRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}