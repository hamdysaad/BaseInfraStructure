package com.mte.infrastructurebase.forms.fields_views.radio_group

import android.widget.CheckBox
import android.widget.RadioGroup
import com.mte.infrastructurebase.forms.FormField
import com.mte.infrastructurebase.forms.interfaces.IFieldView

class FormRadioGroupField(
    val radioGroup: RadioGroup,
    iFieldValue : IFieldView<String?>) : FormField<String?>(iFieldValue) {

    var checkOptions: List<CheckOptions>? = null

    var onCheckChangeListener : RadioGroup.OnCheckedChangeListener? = null


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

    init {
        init()
    }


    private fun init() {

        radioGroup.setOnCheckedChangeListener { p0, p1 ->
            onCheckChangeListener?.onCheckedChanged(p0 , p1)
            attrChangeListener?.onChange()
        }

    }

//    override fun setValue(text: String?) {
//        try {
//            check(getCheckedId(text))
//        }catch (ex : Exception){
//            ex.printStackTrace()
//        }
//    }
//
//    override fun getValue(): String? {
//        return getCheckValue(checkedRadioButtonId)
//    }
}