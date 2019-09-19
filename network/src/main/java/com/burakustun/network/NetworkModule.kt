package com.burakustun.network

import com.burakustun.network.factories.CommonFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by burakustun on 2019-09-19
 */

val networkModule = module {

    single { createHttpLoggingInterceptor(get()) }
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
    single { createWebService<CommonFactory>(get()) }
}


fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(createHttpLoggingInterceptor(BuildConfig.DEBUG))
        .build()
}

private fun createHttpLoggingInterceptor(debugMode : Boolean) : HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if(debugMode) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    return httpLoggingInterceptor

}

fun createRetrofit(okHttpClient: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(com.burakustun.core.BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}