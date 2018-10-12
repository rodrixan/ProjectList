package com.example.rodri.projectlist.rest.callback


import com.example.rodri.projectlist.rest.exception.NullBodyException
import com.example.rodri.projectlist.rest.model.ErrorResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.net.HttpURLConnection


abstract class ServiceCallback<T>(private val responseCallback: ResponseCallback<T>) : Callback<T> {

    abstract fun parseErrorResponse(response: Response<T>): ErrorResponse

    override fun onResponse(call: Call<T>, response: Response<T>) {
        Timber.d("Request successful? ${response.isSuccessful()}. Canceled? ${call.isCanceled()}. HTTP Code:  ${response.code()}. URL: ${call.request().url().url()}")
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> {
                response.body()?.let {
                    responseCallback.onResponseOK(it)
                } ?: onFailure(call, NullBodyException("${call.request().url().url()}"))
            }
            else -> {
                val errorResponse = parseErrorResponse(response)
                responseCallback.onResponseKO(errorResponse)
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Timber.e("Failure on call: ${t.message}")
        responseCallback.onResponseKO(ErrorResponse())
    }
}