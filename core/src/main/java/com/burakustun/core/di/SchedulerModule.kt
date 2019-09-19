package com.burakustun.core.di

import org.koin.dsl.module

/**
 * Created by burakustun on 2019-09-19
 */

val rxModule = module {
    single { SchedulerImp() }
}