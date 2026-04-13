package com.yogi.driverapp.data.model.responce

data class ProductResponce(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
) {

    data class Product(
        val id: Int,
        val title: String,
        val thumbnail: String
    )

}