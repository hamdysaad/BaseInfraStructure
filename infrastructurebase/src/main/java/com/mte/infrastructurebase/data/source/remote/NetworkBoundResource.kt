package com.mte.infrastructurebase.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mte.infrastructurebase.App
import com.mte.infrastructurebase.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception


abstract class NetworkBoundResource<ResultType, ResponseType, ERROR_RESSULT> constructor(private val contextProviders: ContextProviders) {

    var itemsData: ResultType? = null

    private val result = MediatorLiveData<Resource<ResultType , ERROR_RESSULT>>()


    init {
        recreateCall()
    }

     fun recreateCall() {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb() ?: MutableLiveData<ResultType>().apply { setValue(null)}
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType , ERROR_RESSULT>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    GlobalScope.launch(contextProviders.IO) {
                        saveCallResult(processResponse(response))
                        GlobalScope.launch(contextProviders.Main) {
                            result.addSource(loadFromDb()?: MutableLiveData<ResultType>().apply { setValue(getResult()) }) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    GlobalScope.launch(contextProviders.Main) {
                        result.addSource(loadFromDb()?: MutableLiveData<ResultType>()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }

                is ApiErrorBodyResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(getErrorBody(response.errorBody) , newData , createErrorBodyResult(response.errorBody)))
                    }
                }
            }
        }
    }

    open fun createErrorBodyResult(errorBody: String?): ERROR_RESSULT? = null

    open fun getErrorBody(errorBody: String?): String? {
        Timber.e("createErrorBodyResult $errorBody")
        return try {
            Gson().fromJson(errorBody  , DefaultAppErrorBaseResponse::class.java).getError()
        }catch (ex : Exception){
            null
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType , ERROR_RESSULT>>

    abstract fun saveCallResult(item: ResponseType)

    open fun getResult() : ResultType? = itemsData

    protected fun processResponse(response: ApiSuccessResponse<ResponseType>) = response.body

    abstract fun createCall(): LiveData<ApiResponse<ResponseType>>

    protected open fun shouldFetch(data: ResultType?): Boolean = true

    protected open fun loadFromDb(): LiveData<ResultType>? = null

}