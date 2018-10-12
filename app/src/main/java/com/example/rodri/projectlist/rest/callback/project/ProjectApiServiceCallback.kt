package com.example.rodri.projectlist.rest.callback.project

import com.example.rodri.projectlist.rest.callback.ResponseCallback
import com.example.rodri.projectlist.rest.callback.ServiceCallback
import com.example.rodri.projectlist.rest.model.ErrorResponse
import com.example.rodri.projectlist.rest.model.ProjectApiErrorResponse
import com.example.rodri.projectlist.rest.service.ProjectApiService
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

    private fun convertToAppErrorResponse(apiError: ProjectApiErrorResponse) = ErrorResponse(apiError.errorMessage)
}