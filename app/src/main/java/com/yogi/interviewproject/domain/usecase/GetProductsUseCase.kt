package com.yogi.interviewproject.domain.usecase

import com.yogi.interviewproject.data.model.responce.ProductResponce
import com.yogi.interviewproject.domain.repository.PostRepository
import com.yogi.interviewproject.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(limit: Int, skip: Int): ProductResponce {
        return repository.getProducts(limit, skip)
    }
}