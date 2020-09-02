package com.mte.infrastructurebase.form_validation

import androidx.annotation.StringRes
import com.mte.infrastructurebase.interfaces.IRule
import java.lang.Exception


class GreaterThanZero(val message: String?= null, @StringRes val strRes: Int? = null) :
    IRule<String?> {

    override fun validate(value: String?): Any? {

        return try {

            val valueInt =  value?.toFloat()

            if((valueInt?.compareTo(0f) ?: 0) > 0)
                null
            else
                message ?: strRes


        }catch (ex : Exception){
            message ?: strRes
        }
    }

    override fun getMsgString(): String? {
        return message
    }

    override fun getMsgStringRes(): Int? {
        return strRes
    }
}