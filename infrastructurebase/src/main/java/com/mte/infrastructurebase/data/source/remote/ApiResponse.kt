package com.mte.infrastructurebase.data.source.remote


import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.stream.MalformedJsonException
import com.mte.infrastructurebase.App
import com.mte.infrastructurebase.R
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.regex.Pattern
import kotlin.String as String1

 sealed class ApiResponse<T> {

    companion object {

        private val TAG: kotlin.String = "ApiResponse"

        @SuppressLint("LogNotTimber")
        fun <T> create(error: Throwable): ApiErrorResponse<T?> {
            Log.e("ApiResponse", error.message ?: "")
            return ApiErrorResponse(ApiServiceFactory.errorHandler?.getHttpExceptionError(error))
        }

        fun <T> create(response: Response<T>): ApiResponse<T?> {

            val body = response.body()

            if (body is BaseResponseModel)
                body.setResponsCode(response.code())

            return if (response.isSuccessful) {

                if (body is BaseResponseModel && body.isSuccess())
                    ApiSuccessResponse(response.body() )
                else if (body is BaseResponseModel && body.isError()) {
                    ApiErrorResponse(body.getError())
                } else if (body != null && body is BaseResponseModel && body.isEmpty()) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }

            } else {

                val errorBody = response.errorBody()?.string()

                val errorFromBody = ApiServiceFactory.errorHandler?.getErrorFromBody(errorBody)

                val errorMsg = if(errorBody == null) response.message() else errorFromBody

                ApiErrorResponse(errorMsg)
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T >(val errorMessage: String1?) : ApiResponse<T>()