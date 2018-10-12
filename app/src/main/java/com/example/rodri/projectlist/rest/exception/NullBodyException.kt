package com.example.rodri.projectlist.rest.exception

import java.lang.Exception

data class NullBodyException(val url: String="url_not_found") : Exception("Received empty body from request: $url")