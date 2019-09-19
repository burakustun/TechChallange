package com.burakustun.techchallange.orders

import com.burakustun.network.factories.OrderFactory
import io.reactivex.Single

/**
 * Created by burakustun on 2019-09-19
 */
class OrdersInteractor(private val orderFactory: OrderFactory) {

    fun getOrders() : Single<List<Any>> {
        return orderFactory.getOrders()
    }

}