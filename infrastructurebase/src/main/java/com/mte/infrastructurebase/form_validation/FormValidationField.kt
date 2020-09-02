package com.mte.infrastructurebase.form_validation

import com.mte.infrastructurebase.interfaces.IRule


class FormValidationField<T> (val value  : T, val  formControlResID : Int, val rules : List<IRule<T>> ? = null)