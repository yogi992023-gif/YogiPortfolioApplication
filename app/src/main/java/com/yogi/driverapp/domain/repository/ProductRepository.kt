package com.yogi.driverapp.domain.repository

import com.yogi.driverapp.data.model.responce.ProductResponce

interface ProductRepository {

    suspend fun getProducts(limit: Int, skip: Int): ProductResponce

}