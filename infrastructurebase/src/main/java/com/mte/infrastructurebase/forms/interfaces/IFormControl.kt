package com.mte.infrastructurebase.forms.interfaces

import android.view.View

interface IFormControl {
    fun isValid(): Boolean
    fun getTag(): String?
    fun getView(): View?
    fun getValidationView(): IValidationView?
    fun getFiledView(): IFieldView<*>?
}