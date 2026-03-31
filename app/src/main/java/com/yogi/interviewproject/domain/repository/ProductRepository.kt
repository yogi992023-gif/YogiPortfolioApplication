package com.yogi.interviewproject.domain.repository

import com.yogi.interviewproject.data.model.responce.ProductResponce

interface ProductRepository {

    suspend fun getProducts(limit: Int, skip: Int): ProductResponce

}