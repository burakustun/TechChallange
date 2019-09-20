package com.burakustun.techchallange.orders

import com.burakustun.core.extensions.toMonthName
import com.burakustun.data.domain.OrderDomain
import com.burakustun.data.domain.ProductDetailDomain
import com.burakustun.data.domain.ProductState
import com.burakustun.network.factories.OrderFactory
import io.reactivex.Single
import java.util.*

/**
 * Created by burakustun on 2019-09-19
 */
class OrdersInteractor(private val orderFactory: OrderFactory) {

    fun getOrders(): Single<List<OrderDomain>> {
        return orderFactory.getOrders().map {
            //map dto to domain
            it.map { order ->
                OrderDomain(
                    order.date,
                    order.month.toMonthName(), //converts string to month name
                    order.marketName,
                    order.orderName,
                    order.productPrice,
                    getProductState(order.productState.toLowerCase(Locale.getDefault())), //get enum value
                    ProductDetailDomain(
                        order.productDetail.orderDetail,
                        order.productDetail.summaryPrice
                    ),
                    false
                )
            }
        }
    }

    //return enum value according to string returned by service call
    private fun getProductState(stateString: String): ProductState {
        return when (stateString) {
            "yolda" -> ProductState.ON_THE_ROAD
            "hazırlanıyor" -> ProductState.PREPARING
            "onay bekliyor" -> ProductState.WAIT_FOR_APPROVAL
            else -> ProductState.ERROR
        }
    }

}