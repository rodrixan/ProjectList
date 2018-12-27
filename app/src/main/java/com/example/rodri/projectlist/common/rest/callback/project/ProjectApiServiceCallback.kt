package com.example.rodri.projectlist.common.rest.callback.project

import com.example.rodri.projectlist.common.rest.callback.ResponseCallback
import com.example.rodri.projectlist.common.rest.callback.ServiceCallback
import com.example.rodri.projectlist.common.rest.model.ErrorResponse
import com.example.rodri.projectlist.common.rest.model.ProjectApiErrorResponse
import com.example.rodri.projectlist.common.rest.service.ProjectApiService
import retrofit2.Response
import timber.log.Timber

class ProjectApiServiceCallback<T>(responseCallback: ResponseCallback<T>) : ServiceCallback<T>(responseCallback) {

    override fun parseErrorResponse(response: Response<T>): ErrorResponse {
        val converter = ProjectApiService.getRetrofit()
            .responseBodyConverter<ProjectApiErrorResponse>(
                ProjectApiErrorResponse::class.java,
                arrayOfNulls<Annotation>(0)
            )
        response.errorBody()?.let {
            try {
                val serviceError = converter.convert(it)
                return convertToAppErrorResponse(serviceError)
            } catch (e: Exception) {
                Timber.e("Error while parsing error response: ${e.message}")
                return ErrorResponse("")
            }
        } ?: return ErrorResponse("")
    }

    private fun convertToAppErrorResponse(apiError: ProjectApiErrorResponse?) =
        ErrorResponse(apiError?.errorMessage ?: "Null error")
}