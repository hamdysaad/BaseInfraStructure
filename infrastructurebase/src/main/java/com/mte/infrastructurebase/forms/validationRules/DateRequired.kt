package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule
import java.util.*

class DateRequired(val message: String?) : IRule<Date> {

    override fun validate(value: Date?): String? {

        return if(value != null)
            null
        else
            message

    }

}