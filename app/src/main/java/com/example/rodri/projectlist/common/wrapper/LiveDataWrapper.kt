package com.example.rodri.projectlist.common.wrapper

import android.arch.lifecycle.LiveData
import com.example.rodri.projectlist.common.rest.model.ErrorResponse

/**
 * Used for all repositories for better error handling
 */
data class LiveDataWrapper<T>(
    var isSuccessful: Boolean = false,
    var responseData: T? = null,
    var errorResponse: ErrorResponse? = null

)