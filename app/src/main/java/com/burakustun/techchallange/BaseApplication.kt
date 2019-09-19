package com.burakustun.techchallange

import android.app.Application
import com.burakustun.core.di.rxModule
import com.burakustun.network.networkModule
import com.burakustun.techchallange.login.loginModule
import com.burakustun.techchallange.orders.ordersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by burakustun on 2019-09-19
 */
class BaseApplication : Application() {

    private val moduleList = listOf(
        rxModule,
        appModule,
        networkModule,
        loginModule,
        ordersModule
    )

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(moduleList)
        }
    }

}