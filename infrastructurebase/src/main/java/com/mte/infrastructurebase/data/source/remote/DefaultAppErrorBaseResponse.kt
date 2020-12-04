package com.mte.infrastructurebase.data.source.remote



class DefaultAppErrorBaseResponse : BaseResponseModel() {


    var errorMessage : String? = null


    override fun getSuccess(): Any? {return false}


    override fun getError(): String? {
        return errorMessage
    }

    override fun isEmpty(): Boolean? = false
}
