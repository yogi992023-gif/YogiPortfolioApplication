package com.yogi.driverapp.domain.usecase

import com.yogi.driverapp.data.model.responce.ProductResponce
import com.yogi.driverapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(limit: Int, skip: Int): ProductResponce {
        return repository.getProducts(limit, skip)
    }
}