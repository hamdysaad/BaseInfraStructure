package com.mte.infrastructurebase.data.source.remote


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class BasicNetworkBoundResource<ResultType, ResponseType , ERROR_RESSULT> constructor(private val contextProviders: ContextProviders) {

    var itemsData: ResultType? = null


    init {
        recreateCall()
    }

     fun recreateCall() {

         GlobalScope.launch(contextProviders.IO){
             onResource(Resource.loading(null))
             val dataFromDB = loadFromDb()
             onResource(Resource.loading(dataFromDB))
             if (shouldFetch(dataFromDB)) {
                 fetchFromNetwork(dataFromDB)
             } else {
                 onResource(Resource.success(dataFromDB))
             }
         }
    }


    private fun fetchFromNetwork(data : ResultType?) {



            createCall().enqueue(object : Callback<ResponseType>{
                override fun onFailure(call: Call<ResponseType>, t: Throwable) {
                    onFetchFailed()
                    onResource(Resource.error(ApiServiceFactory.errorHandler?.getHttpExceptionError(t)))
                }

                override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
                    if (response.isSuccessful) {

                        val body = response.body()

                        if (body is BaseResponseModel && body.getError() != null) {
                            onFetchFailed()
                            onResource(Resource.error(body.getError()))

                        } else if (body == null || (body is BaseResponseModel && body.isEmpty() == true)) {
                            onResource(Resource.empty())
                        } else {
                            saveCallResult(processResponse(response))
                            onResource(Resource.success(loadFromDb() ?: itemsData))
                        }

                    } else {

                        val errorBody = response.errorBody()?.string()

                        onResource(Resource.error(getErrorBody(errorBody) , null , createErrorBodyResult(errorBody)))
                    }
                }
            })
    }

    abstract fun onResource(resource : Resource<ResultType, ERROR_RESSULT>)

    abstract fun createErrorBodyResult(errorBody: String?): ERROR_RESSULT?

    abstract fun getErrorBody(errorBody: String?): String?

    protected open fun onFetchFailed() {}

    abstract fun saveCallResult(item: ResponseType?)

    abstract fun getResult() : ResultType?

    protected fun processResponse(response: Response<ResponseType>) = response.body()

    abstract fun createCall(): Call<ResponseType>


    protected open fun shouldFetch(data: ResultType?): Boolean = true

    protected open fun loadFromDb(): ResultType? = null

}