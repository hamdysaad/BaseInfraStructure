package com.mte.infrastructurebase.data.source.remote



abstract class BaseResponseModel{

   abstract fun isSuccess() : Boolean
   abstract fun getError() : String?
    abstract fun setResponsCode(code: Int)
    abstract fun isError(): Boolean
    abstract fun isEmpty(): Boolean
}