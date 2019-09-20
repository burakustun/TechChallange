package com.burakustun.techchallange.orders

import org.koin.dsl.module

/**
 * Created by burakustun on 2019-09-19
 */

val ordersModule = module {
    single { OrdersInteractor(get()) }
    single { OrdersViewModel(get(), get()) }
}