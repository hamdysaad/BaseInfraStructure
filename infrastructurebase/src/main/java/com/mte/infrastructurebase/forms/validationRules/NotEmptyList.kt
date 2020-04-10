package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule

class NotEmptyList(val message: String?) : IRule<List<Any?>?> {

    override fun validate(value: List<Any?>?): String? {

        return if(value.isNullOrEmpty())
            message
        else
            null

    }

}