package com.burakustun.core.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by burakustun on 2019-09-19
 */

class SchedulerImp : Scheduler {

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.io()
    }

    override fun main(): io.reactivex.Scheduler {
        return AndroidSchedulers.mainThread()
    }
}