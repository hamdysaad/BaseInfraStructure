package com.mte.infrastructurebase.forms

import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule

open class FormField<T>(val iFieldView : IFieldView<T?>) : IFieldView<T> {


     var attrChangeListener : InverseBindingListener? = null

     var rules :  List<IRule<T>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

    override fun isValid(): Boolean {

        validationMessages?.clear()

        rules?.forEach {
            val message = it.validate(getValue())
            if (message != null)
                validationMessages?.add(message)
        }

        return validationMessages?.size == 0
    }

    override fun getValidationMessage(): String? {
       return validationMessages?.get(0)
    }



    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChangeListener = attrChange
    }


    override fun setValue(value: T?) {
        iFieldView.setValue(value)
    }

    override fun getValue(): T? {
        return iFieldView.getValue()
    }

}