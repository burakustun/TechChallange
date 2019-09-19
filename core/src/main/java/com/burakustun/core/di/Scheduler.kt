package com.burakustun.core.di

import io.reactivex.Scheduler

/**
 * Created by burakustun on 2019-09-19
 */

interface Scheduler {
    fun io(): Scheduler

    fun main(): Scheduler
}