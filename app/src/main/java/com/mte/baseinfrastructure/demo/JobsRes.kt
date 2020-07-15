package com.mte.baseinfrastructure.demo

import com.mte.baseinfrastructure.network.DemoResponseModel

class JobsRes : DemoResponseModel(){

    val data : List<JobModel>? = null
    override fun isSuccess(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getError(): String? {
        TODO("Not yet implemented")
    }

    override fun setResponsCode(code: Int) {
        TODO("Not yet implemented")
    }

    override fun isError(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

}