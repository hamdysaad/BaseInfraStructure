package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.fields_views.date_picker.DatePickerFieldView
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule

class IsDateGTOrEqualCurrent(
    val datefieldView: IFieldView<String?>?,
    val message: String?) : IRule<String> {

    override fun validate(value: String?): String? {


        if(datefieldView is DatePickerFieldView ){

            val startDate = datefieldView.dateTimestamp ?: return null

            val currentDate = datefieldView.currentDate

            if(startDate.time >= (currentDate?.time ?: 0)){
                return null
            }
            return message

        }

        return null

    }

}