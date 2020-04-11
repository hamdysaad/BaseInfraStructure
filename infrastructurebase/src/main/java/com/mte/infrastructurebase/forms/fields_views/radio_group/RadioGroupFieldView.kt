package com.mte.infrastructurebase.forms.fields_views.radio_group

import android.content.Context
import android.util.AttributeSet
import android.widget.*
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.forms.FormField
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.IRule

import java.lang.Exception

open class RadioGroupFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : RadioGroup(context, attributeSet) , IFieldView<String?> {


    var formField: FormField<String> = FormField(this)


    var checkOptions: List<CheckOptions>? = null

    private var attrChange: InverseBindingListener? = null

    var rules :  List<IRule<String>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

    var onCheckChangeListener : OnCheckedChangeListener? = null


    init {
        init()
    }

    private fun init() {

        setOnCheckedChangeListener(object : OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                onCheckChangeListener?.onCheckedChanged(p0 , p1)
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

    override fun setValue(text: String?) {
        try {
           check(getCheckedId(text))
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    override fun getValue(): String? {
        return getCheckValue(checkedRadioButtonId)
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        formField.attrChangeListener
    }


    fun getCheckValue(checkedRadioButtonId: Int): String? {
        var checkValue : String? = null

        checkOptions?.forEach {
            if(checkedRadioButtonId == it.id)
                checkValue = it.value
        }
        return checkValue
    }

    fun getCheckedId(text: String?): Int {

        var id = 0

        checkOptions?.forEach {
            if(text?.equals(it.value) == true)
                id = it.id
        }

        return id
    }


}