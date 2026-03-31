package com.yogi.interviewproject.domain.usecase

import com.yogi.interviewproject.data.model.responce.Category
import com.yogi.interviewproject.domain.repository.PostRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}