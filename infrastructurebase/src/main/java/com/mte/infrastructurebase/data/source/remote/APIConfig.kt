package com.mte.infrastructurebase.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.HashMap


abstract class APIConfig {

    abstract fun getHost() : String
    abstract fun getBaseUrl() : String
    abstract fun <T> getApiService() :  Class<T>
    abstract fun getHeaders(): HashMap<String, String>?
    abstract fun getErrorHandler(): ErrorHandler?
    abstract fun addOKHttpConfig(client: OkHttpClient.Builder)
    abstract fun addRetrofitConfig(retrofitBuilder: Retrofit.Builder)

}