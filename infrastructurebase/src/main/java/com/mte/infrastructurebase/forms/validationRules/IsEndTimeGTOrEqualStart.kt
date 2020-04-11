package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.fields_views.time_picker.TimePickerFieldView
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule

class IsEndTimeGTOrEqualStart(
    val startDatefieldView: IFieldView<String?>?,
    val endDatefieldView: IFieldView<String?>?,
    val message: String?) : IRule<String> {

    override fun validate(value: String?): String? {


        if(startDatefieldView is TimePickerFieldView && endDatefieldView is TimePickerFieldView ){

            val startDate = startDatefieldView.dateTimestamp

            val endDate = endDatefieldView.dateTimestamp

            if(startDate == null || endDate == null) return null

            if(startDate.time <= endDate.time){
                return null
            }

            return message

        }

        return null

    }

}