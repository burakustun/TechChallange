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

    fun getOrders() {
        launchRequest {
            interactor.getOrders()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .subscribe({
                    ordersMLD.value = Result.Success(it)
                }, {
                    errorLiveData.value = Result.Error("Bir hata ile karşılaşıldı")
                })
        }
    }

}