package com.burakustun.network.factories

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by burakustun on 2019-09-19
 */
interface OrderFactory {

    @GET("/")
    fun getOrders() : Single<List<Any>>

}