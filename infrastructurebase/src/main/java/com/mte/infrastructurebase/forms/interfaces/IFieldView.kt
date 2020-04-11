package com.mte.infrastructurebase.forms.interfaces

import androidx.databinding.InverseBindingListener

interface IFieldView<T> {
    fun isValid(): Boolean
    fun getValidationMessage(): String?
    fun setValue(value: T?)
    fun getValue(): T?
    fun setAttrChange(attrChange: InverseBindingListener)
}