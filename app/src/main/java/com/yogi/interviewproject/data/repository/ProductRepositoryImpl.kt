package com.yogi.interviewproject.data.repository

import com.yogi.interviewproject.data.model.responce.ProductResponce
import com.yogi.interviewproject.data.remote.ProductService
import com.yogi.interviewproject.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val api: ProductService) : ProductRepository {

    override suspend fun getProducts(limit: Int, skip: Int): ProductResponce {
        return api.getProducts(limit, skip)
    }
}