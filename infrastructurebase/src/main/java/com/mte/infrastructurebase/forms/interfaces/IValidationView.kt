package com.mte.infrastructurebase.forms.interfaces

interface IValidationView {
    fun resetValidationView()
    fun showValidateError()
    fun getValidationMessage() : String?
    fun setMessage(validationMessage: String?)
}