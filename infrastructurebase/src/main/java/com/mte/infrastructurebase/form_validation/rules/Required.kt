package com.mte.infrastructurebase.form_validation.rules

import androidx.annotation.StringRes
import com.mte.infrastructurebase.interfaces.IRule


class Required(val message: String?= null , @StringRes val strRes: Int? = null) : IRule<String?> {

    override fun validate(value: String?): Any? {

        return if(!value.isNullOrEmpty() && !value.isNullOrBlank())
            null
        else
            message ?: strRes
    }

    override fun getMsgString(): String? {
        return message
    }

    override fun getMsgStringRes(): Int? {
        return strRes
    }

}