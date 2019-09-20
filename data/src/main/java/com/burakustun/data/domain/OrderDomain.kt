package com.burakustun.data.domain

import com.burakustun.data.dto.ProductDetailDto

/**
 * Created by burakustun on 2019-09-19
 */

data class OrderDomain(
    val date: String,
    val month: String,
    val marketName: String,
    val orderName: String,
    val productPrice: Double,
    val productState: ProductState,
    val productDetail: ProductDetailDomain,
    var isDetailSectionShowing : Boolean
)

enum class ProductState(val code: String) {
    ON_THE_ROAD("Yolda"), PREPARING("Hazırlanıyor"), WAIT_FOR_APPROVAL("Onay Bekliyor"),ERROR("Hata")
}