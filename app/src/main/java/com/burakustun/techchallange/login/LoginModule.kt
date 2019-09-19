package com.burakustun.techchallange.login

import org.koin.dsl.module

/**
 * Created by burakustun on 2019-09-19
 */

val loginModule = module {
    single { LoginViewModel() }
}