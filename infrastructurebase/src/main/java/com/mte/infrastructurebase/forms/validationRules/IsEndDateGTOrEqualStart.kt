package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.fields_views.date_picker.DatePickerFieldView
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule

class IsEndDateGTOrEqualStart(
    val startDatefieldView: IFieldView<String?>?,
    val endDatefieldView: IFieldView<String?>?,
    val message: String?) : IRule<String> {

    override fun validate(value: String?): String? {


        if(startDatefieldView is DatePickerFieldView && endDatefieldView is DatePickerFieldView ){

            val startDate = startDatefieldView.dateTimestamp

            val endDate = endDatefieldView.dateTimestamp


            if(startDate == null || endDate == null) return null

            val currentDate = startDatefieldView.currentDate

            if(startDate.time >= (currentDate?.time ?: 0) &&  startDate.time <= endDate.time){
                return null
            }

            return message

        }

        return null

    }

}