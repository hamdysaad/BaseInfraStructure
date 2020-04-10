package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule
import java.io.File

class FileRequired(val message: String?) : IRule<File> {

    override fun validate(value: File?): String? {

        return if(value != null)
            null
        else
            message

    }

}