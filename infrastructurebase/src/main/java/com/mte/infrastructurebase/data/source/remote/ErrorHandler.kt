package com.mte.infrastructurebase.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.stream.MalformedJsonException
import com.mte.infrastructurebase.App
import com.mte.infrastructurebase.R
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Your name on 1/28/2020.
 */
interface ErrorHandler {

    fun getErrorFromBody(errorBody: String?): String?


    fun getHttpExceptionError(error: Throwable): String?{
        Timber.e(error)
        return  "An error occurred"
    }
}