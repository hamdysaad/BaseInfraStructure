package com.mte.infrastructurebase.form_validation.rules

import androidx.annotation.StringRes
import com.mte.infrastructurebase.interfaces.IRule
import java.lang.Exception

class Between(val message: String? , val from : Int , val to : Int , @StringRes val strRes: Int? = null) : IRule<String?> {

    override fun validate(value: String?): String? {


        return try {

            if(value.isNullOrEmpty()) return message

            val valueInt = value.toInt()

            if(valueInt in from..to)
                null
            else
                message

        }catch (ex:Exception){
            message
        }



    }

    override fun getMsgStringRes(): Int?  = strRes

    override fun getMsgString(): String?  = message

}