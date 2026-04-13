package com.yogi.driverapp.data.remote

import com.yogi.driverapp.data.model.responce.ProductResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int, @Query("skip") skip: Int) : ProductResponce

}