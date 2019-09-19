package com.burakustun.core.viewmodel

/**
 * Created by burakustun on 2019-09-19
 */

sealed class Result<out T> {

    class Success<T>(val value: T) : Result<T>()

    class Error(val error: String) : Result<String>()

    object Loading : Result<Nothing>()

    fun onSuccess(successHandler: (T) -> Unit): Result<T> = this.also {
        when (this) {
            is Success -> successHandler(value)
        }
    }

    fun onError(errorHandler: (String) -> Unit): Result<T> = this.also {
        when (this) {
            is Error -> errorHandler(error)
        }
    }

    fun onLoading(loadingHandler : () -> Unit): Result<T> = this.also {
        when (this)
        {
            is Loading -> loadingHandler()
        }
    }
}