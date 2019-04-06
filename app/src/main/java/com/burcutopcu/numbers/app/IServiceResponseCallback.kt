package com.burcutopcu.numbers.app

interface IServiceResponseCallback<T> {

    fun onServerError(error: String?)

    fun onServerCompleted(t: T) {
    }

    fun onServerCompleted() {
    }

}