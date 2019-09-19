package com.burakustun.techchallange.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.burakustun.core.di.Scheduler
import com.burakustun.core.viewmodel.BaseViewModel
import com.burakustun.core.viewmodel.Result
import com.burakustun.data.domain.OrderDomain
import com.burakustun.data.dto.OrderDto


/**
 * Created by burakustun on 2019-09-19
 */

class OrdersViewModel(private val interactor: OrdersInteractor, private val scheduler: Scheduler) :
    BaseViewModel() {

    private val ordersMLD = MutableLiveData<Result<List<OrderDomain>>>()

    val ordersLiveData: LiveData<Result<List<OrderDomain>>> get() = ordersMLD

    var orders : ArrayList<OrderDomain> = arrayListOf()

    fun getOrders() {
        launchRequest {
            interactor.getOrders()
                .subscribeOn(scheduler.io())
                .doOnSubscribe { ordersMLD.value = Result.Loading }
                .observeOn(scheduler.main())
                .subscribe({
                    orders.clear()
                    orders.addAll(it)
                    ordersMLD.value = Result.Success(orders)
                }, {
                    errorLiveData.value = Result.Error("Bir hata ile karşılaşıldı")
                })
        }
    }

}