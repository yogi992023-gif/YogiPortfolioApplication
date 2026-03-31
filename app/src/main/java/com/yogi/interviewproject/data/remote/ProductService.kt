package com.yogi.interviewproject.data.remote

import com.yogi.interviewproject.data.model.responce.ProductResponce
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductService {

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int, @Query("skip") skip: Int) : ProductResponce

}