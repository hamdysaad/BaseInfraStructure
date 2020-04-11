package com.mte.infrastructurebase.forms.fields_views.text_field

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.forms.FormField
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.IRule

open class TextFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : EditText(context, attributeSet),
    IFieldView<String?> {


    var formField: FormField<String> = FormField(this)


     var rules :  List<IRule<String>>? = null
         set(value) {
             formField.rules = value
             field = value
         }


    override fun isValid(): Boolean {
        return formField.isValid()
    }

    override fun getValidationMessage(): String? {
        return formField.getValidationMessage()
    }

    override fun setValue(text: String?) {
       setText(text)
    }

    override fun getValue(): String? {
        return text.toString().trim()
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        formField.setAttrChange(attrChange)
    }


}