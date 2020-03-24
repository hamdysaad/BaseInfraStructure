package com.mte.infrastructurebase.form_view.fields_views

import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.IRule
import com.mte.infrastructurebase.utils.KeyboardUtils
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

open class TimePickerFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextView(context, attributeSet) , IFieldView<String?>,TimePickerDialog.OnTimeSetListener {


    var displayDatText: String? = null
    var time: String? = null
    var dateTimestamp: Date? = null
    var is24: Boolean = true

    open var displayTimeFormat: String = "hh:mm aa"
    //    open var timeFormat: String = displayTimeFormat
    open var timeFormat: String = "HH:mm"

    var initialCurrentDate : Boolean    = false
        set(value) {
            field = value
            if(field)
                initCurrentDate()

        }


    var viewToClick : View?= null
        set(value) {
            field = value
            field?.setOnClickListener(null)
            field?.setOnClickListener {
                openTimePickerDialog()
            }

        }

    private lateinit var myCalender: Calendar
    private lateinit var timePickerDialog: TimePickerDialog


    private var attrChange: InverseBindingListener? = null

    var rules :  List<IRule<String>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

    init {
        Locale.setDefault(Locale.ENGLISH)
        init()
    }

    private fun init() {

        viewToClick = this

        myCalender = Calendar.getInstance(Locale.ENGLISH)

        if(initialCurrentDate)
            initCurrentDate()

    }

    private fun initCurrentDate() {
        myCalender = Calendar.getInstance(Locale.ENGLISH)

        val hourOfDay = myCalender[Calendar.HOUR_OF_DAY]
        val minute = myCalender[Calendar.MINUTE]

        onTimeSet(null , hourOfDay , minute)
    }

    private fun openTimePickerDialog() {

        KeyboardUtils.hideKeyboardInDialogFragment(this)

        val hourOfDay = myCalender[Calendar.HOUR_OF_DAY]
        val minute = myCalender[Calendar.MINUTE]


        timePickerDialog = TimePickerDialog(
            context,
            this,
            hourOfDay,
            minute,
            is24
        )

        timePickerDialog.show()
    }

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

    override fun setValue(text: String?) {
        try {

            //Parse value to date
            val sdf  = SimpleDateFormat(timeFormat, Locale.US)
            dateTimestamp = sdf.parse(text!!)
            myCalender.time = dateTimestamp!!

            time = getTimeText()
            displayDatText = getDisplayTimeText()

            setText(displayDatText)

        } catch (ex : Exception) {
            ex.printStackTrace()
            time = null
            displayDatText = null
            setText(displayDatText)
        }

    }

    private fun getTimeText(): String? {
        if(dateTimestamp != null) {
            //dateFormat
            var sdf = SimpleDateFormat(timeFormat, Locale.US)
            return  sdf.format(dateTimestamp!!)
        }

        return null
    }

    fun getDisplayTimeText(): String? {
        if(dateTimestamp != null) {
            //dateFormat
            val sdf = SimpleDateFormat(displayTimeFormat, Locale(Locale.getDefault().language))
            return  sdf.format(dateTimestamp!!)
        }

        return null
    }


    override fun getValue(): String? {
        return time
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChange = attrChange
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myCalender[Calendar.HOUR_OF_DAY] = hourOfDay
        myCalender[Calendar.MINUTE] = minute


        dateTimestamp =  myCalender.time

        time = getTimeText()

        setValue(time)

        attrChange?.onChange()

    }

    override fun setFormControl(formControl: IFormControl?) {}
}