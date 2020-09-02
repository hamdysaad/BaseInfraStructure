package com.mte.infrastructurebase.form_validation

import com.mte.infrastructurebase.interfaces.IRule

class FormValidationError(val messages  : List<IRule<*>?>, val  formControlResID : Int)