package com.burakustun.data.dto

/**
 * Created by burakustun on 2019-09-19
 */
data class OrderDto(
    val date: String,
    val month: String,
    val marketName: String,
    val orderName: String,
    val productPrice: Double,
    val productState: String,
    val productDetail: ProductDetailDto
)