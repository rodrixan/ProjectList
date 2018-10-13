package com.example.rodri.projectlist.common.wrapper

import androidx.lifecycle.Observer

class ObserverWrapper<T>(private val onChangeListener: ObserverCallbacks<T>) : Observer<LiveDataWrapper<T>> {

    override fun onChanged(observedData: LiveDataWrapper<T>?) {
        observedData?.let {
            with(it) {
                if (isSuccessful && responseData != null) {
                    onChangeListener.onChange(responseData!!)
                } else {
                    val message = errorResponse?.errorMessage ?: null
                    onChangeListener.onError(message)
                }
            }
        } ?: onChangeListener.onError(null)
    }

    interface ObserverCallbacks<T> {
        fun onChange(data: T)
        fun onError(errorMessage: String?)
    }
}