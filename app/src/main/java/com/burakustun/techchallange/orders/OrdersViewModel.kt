package com.burakustun.techchallange.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.burakustun.core.di.Scheduler
import com.burakustun.core.viewmodel.BaseViewModel
import com.burakustun.core.viewmodel.Result
import com.burakustun.data.domain.OrderDomain


/**
 * Created by burakustun on 2019-09-19
 */

class OrdersViewModel(private val interactor: OrdersInteractor, private val scheduler: Scheduler) :
    BaseViewModel() {

    private val ordersMLD = MutableLiveData<Result<List<OrderDomain>>>()
    //cast mutable live data to live data so that it can not be modified from ui
    val ordersLiveData: LiveData<Result<List<OrderDomain>>> get() = ordersMLD

    //order list
    var orders: ArrayList<OrderDomain> = arrayListOf()

    fun getOrders() {
        //add to composite disposable
        launchRequest {
            //service call
            interactor.getOrders()
                .subscribeOn(scheduler.io()) //subscribe on io thread
                .doOnSubscribe { ordersMLD.value = Result.Loading } //send loading event
                .observeOn(scheduler.main()) //observe on main thread
                .subscribe({
                    //clear list
                    orders.clear()
                    //add items
                    orders.addAll(it)
                    //send success event and data
                    ordersMLD.value = Result.Success(orders)
                }, {
                    //send error event
                    errorLiveData.value = Result.Error("Bir hata ile karşılaşıldı")
                })
        }
    }

}