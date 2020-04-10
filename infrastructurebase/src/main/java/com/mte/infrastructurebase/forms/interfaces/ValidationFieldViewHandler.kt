package com.mte.infrastructurebase.forms.interfaces

import com.mte.infrastructurebase.forms.interfaces.IFormControl


interface ValidationFieldViewHandler {

    fun onSuccess()
    fun onFailure(fields: ArrayList<IFormControl?>)
}