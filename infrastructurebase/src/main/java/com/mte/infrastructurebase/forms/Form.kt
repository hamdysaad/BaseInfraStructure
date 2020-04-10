package com.mte.infrastructurebase.forms

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.IValidationView
import com.mte.infrastructurebase.forms.interfaces.ValidationFieldViewHandler
import java.lang.Exception

class Form() {


    private val fieldsMap = HashMap<String, IFormControl>()
    private val validationFields: ArrayList<IFormControl?> = ArrayList()
    private val fields: ArrayList<IFormControl> = ArrayList()

    fun validate(validationHandler: ValidationFieldViewHandler) {
        validationFields.clear()

        fields.forEach {
            if (!it.isValid())
                validationFields.add(it)
        }

        if (validationFields.size == 0)
            validationHandler.onSuccess()
        else
            validationHandler.onFailure(validationFields)
    }

    fun <F : IFormControl> getFieldsByTag(tag: String?): F? {
        return if (fieldsMap.get(tag) != null) fieldsMap.get(tag) as F else null
    }

    /*Add Field*/
    private fun addFieldToForm(field: IFormControl, postion: Int = -1) {

//        if (!field.getTag().isNullOrEmpty())
//            fieldsMap.put(field.getTag()!!, field)
//
//        if (postion > -1)
//            addView(field.getView(), postion)
//        else
//            addView(field.getView(), postion)

//        field.position = indexOfChild(field) ?: -1
    }

    fun addField(
        tag : String ,
        iFieldView: View,
        iValidationView: View ) {



//        fields.add(field)
//        addFieldToForm(field)
    }

    /*Delete Field*/
    fun deleteField(tag: String) {
        fieldsMap[tag]?.let {
            (it?.getView()?.parent as ViewGroup).removeView(it.getView())
        }

        (fields as ArrayList).remove(getFieldsByTag<IFormControl>(tag))
        fieldsMap.remove(tag)
    }

    fun replaceField(tag: String, baseField: IFormControl?, pos: Int?) {
        if(baseField == null) return
        deleteField(tag)
//        addField(baseField , pos ?: -1)
    }


    private fun addFieldToMap(child: IFormControl) {
        fields.add(child)
        if (!child.getTag().isNullOrEmpty())
            fieldsMap[child.getTag()!!] = child
    }




    companion object{


        @JvmStatic
         fun builder(): Form {
            return Form()
        }
    }




}