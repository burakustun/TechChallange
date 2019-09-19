package com.burakustun.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by burakustun on 2019-09-19
 */

open class BaseViewModel : ViewModel() {

    val subscriptions = CompositeDisposable()

    val errorLiveData = MutableLiveData<Result.Error>()

    fun launchRequest(body : () -> Disposable){
        subscriptions.add(body())
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }

}