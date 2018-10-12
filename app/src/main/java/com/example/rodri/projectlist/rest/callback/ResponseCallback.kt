package com.example.rodri.projectlist.rest.callback

import com.example.rodri.projectlist.rest.model.ErrorResponse
import retrofit2.Response


interface ResponseCallback<T> {
    fun onResponseOK(responseData: T)
    fun onResponseKO(errorResponse: ErrorResponse)
}