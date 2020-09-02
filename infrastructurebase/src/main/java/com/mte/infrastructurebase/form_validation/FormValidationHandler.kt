package com.mte.infrastructurebase.form_validation


interface FormValidationHandler {

    fun onSuccess()
    fun onFailure(fields: ArrayList<FormValidationError?>)
}