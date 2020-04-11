package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule

class ConfirmPassword(val fieldView: IFieldView<String?>, val message: String?) : IRule<String> {

    override fun validate(value: String?): String? {

        return if(fieldView.getValue() == value)
            null
        else
            message

    }

}