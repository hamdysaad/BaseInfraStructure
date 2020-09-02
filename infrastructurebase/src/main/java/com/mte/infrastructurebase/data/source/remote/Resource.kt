package com.mte.infrastructurebase.data.source.remote

import androidx.annotation.StringRes

data class Resource<out T , ERROR_RESSULT>(
    var status: Status,
    val data: T? = null,
    val errorData: ERROR_RESSULT? = null,
    var message: String? = null,
    @StringRes var strRes : Int? = null
) {
    companion object {
        fun <T , ERROR_RESSULT> success(data: T?): Resource<T , ERROR_RESSULT> {
            return Resource(
               status =  Status.SUCCESS,
               data =  data,
                errorData = null,
                message = null
            )
        }

        fun <T , ERROR_RESSULT> error(msg: String? , data: T? = null , errorData: ERROR_RESSULT? = null ): Resource<T , ERROR_RESSULT> {
            return Resource(
                status = Status.ERROR,
                data = data,
                errorData = errorData,
                message = msg
            )
        }

        fun <T  , ERROR_RESSULT> error(@StringRes strRes : Int ,  data: T? = null , errorData: ERROR_RESSULT? = null): Resource<T , ERROR_RESSULT> {
            return Resource(
                status =  Status.ERROR,
                data = data,
                errorData = errorData,
                strRes = strRes
            )
        }

        fun <T , ERROR_RESSULT> loading(data: T?): Resource<T , ERROR_RESSULT> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }

        fun <T , ERROR_RESSULT> empty(): Resource<T , ERROR_RESSULT> {
            return Resource(
                Status.EMPTY,
                null,
                null,
                null
            )
        }

    }
}