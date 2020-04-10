package com.mte.infrastructurebase.data.source.remote



abstract class BaseResponseModel{

   abstract fun getSuccess() : Any?
   abstract fun getError() : String?
   abstract fun isEmpty() : Boolean?
}