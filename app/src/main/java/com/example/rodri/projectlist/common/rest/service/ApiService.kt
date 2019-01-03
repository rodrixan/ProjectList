package com.example.rodri.projectlist.common.rest.service

import com.example.rodri.projectlist.common.data.AppInternalData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.net.HttpURLConnection

interface ApiService<T> {
    val serviceRetrofit: Retrofit
    val serviceApi: T

    fun <U> serviceResponseToAppError(response: Response<U>): AppInternalData.Error

}

// Extension function for conversion to internal app model
suspend fun <T : Any> Deferred<Response<T>>.awaitForResult(errorConverter: ((Response<T>) -> AppInternalData.Error)?): AppInternalData<T> {
    try {
        delay(3000) //Just to show the loading view
        val response = this.await()
        return when (response.code()) {
            HttpURLConnection.HTTP_OK -> response.body()?.let { AppInternalData.Success(it) }
                ?: AppInternalData.Error("Received empty body from request")
            else -> if (errorConverter == null) {
                AppInternalData.Error(response.errorBody()?.string() ?: "Unknown error")
            } else {
                errorConverter(response)
            }
        }
    } catch (e: Exception) {
        Timber.e("Exception in call: $e")
        return AppInternalData.Error("Exception in call: ${e.message}")
    }
}