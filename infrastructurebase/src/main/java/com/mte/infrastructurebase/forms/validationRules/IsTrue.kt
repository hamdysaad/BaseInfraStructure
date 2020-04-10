package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule

class IsTrue(val message: String?) : IRule<Boolean?> {

    override fun validate(value: Boolean?): String? {

        return if(value == null || !value)
            message
        else
            null

    }

}