package com.mte.infrastructurebase.forms.fields_views.checkbox

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.forms.FormField
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.IRule
import java.lang.Exception

open class CheckBoxFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : CheckBox(context, attributeSet) ,
    IFieldView<Boolean?> {


    val formField: FormField<Boolean?> = FormField(this)

    var rules :  List<IRule<Boolean?>>? = null
        set(value) {
            formField.rules = value
            field = value
        }


    init {
        init()
    }

    private fun init() {

        setOnCheckedChangeListener(object : OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                formField.attrChangeListener?.onChange()
            }

        })

    }

    override fun isValid(): Boolean {
        return formField.isValid()
    }

    override fun getValidationMessage(): String? {
       return formField.getValidationMessage()
    }

    override fun setValue(b: Boolean?) {
        try {
            isChecked = b!!
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    override fun getValue(): Boolean? {
        return isChecked
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        formField.setAttrChange(attrChange)
    }

    override fun setFormControl(formControl: IFormControl?) {}


}