package com.yogi.driverapp.data.repository

import com.yogi.driverapp.data.model.responce.ProductResponce
import com.yogi.driverapp.data.remote.ProductService
import com.yogi.driverapp.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val api: ProductService) : ProductRepository {

    override suspend fun getProducts(limit: Int, skip: Int): ProductResponce {
        return api.getProducts(limit, skip)
    }
}