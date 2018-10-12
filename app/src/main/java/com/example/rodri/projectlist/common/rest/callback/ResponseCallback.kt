package com.example.rodri.projectlist.common.rest.callback

import com.example.rodri.projectlist.common.rest.model.ErrorResponse


interface ResponseCallback<T> {
    fun onResponseOK(responseData: T)
    fun onResponseKO(errorResponse: ErrorResponse)
}