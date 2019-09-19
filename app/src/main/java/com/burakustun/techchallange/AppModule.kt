package com.burakustun.techchallange

import com.burakustun.core.utils.ClientPreferences
import org.koin.dsl.module

/**
 * Created by burakustun on 2019-09-19
 */

val appModule = module {
    single { ClientPreferences(get()) }
}