package com.mte.infrastructurebase.forms.fields_views.date_picker

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.forms.FormField
import com.mte.infrastructurebase.forms.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule
import com.mte.infrastructurebase.utils.KeyboardUtils
import java.text.SimpleDateFormat
import java.util.*


open class DatePickerFieldView(context: Context, attributeSet: AttributeSet? = null) :
    TextView(context, attributeSet), IFieldView<String?>, DatePickerDialog.OnDateSetListener {


    var formField: FormField<String> = FormField(this)

    var currentDate: Date? = null
    var dateTimestamp: Date? = null


    open var displayDateFormat: String = "dd-MM-yyyy"
    open var dateFormat: String = displayDateFormat
//    open var dateFormat : String             = "yyyy-MM-dd'T'HH:mm:ss.SSS"

    private var dateText: String? = null
    private var displayDatText: String? = null

    open var initialCurrentDate: Boolean = true
        set(value) {
            field = value
            if (field)
                initCurrentDate()

        }


    private lateinit var myCalender: Calendar
    private lateinit var datePickerDialog: DatePickerDialog


    var rules: List<IRule<String>>? = null
        set(value) {
            formField.rules = value
            field = value
        }

    var viewToClick: View? = null
        set(value) {
            field = value
            field?.setOnClickListener(null)
            field?.setOnClickListener {
                openDatePickerDialog()
            }

        }

    init {

        init()
    }

    private fun init() {

        viewToClick = this

        myCalender = Calendar.getInstance()

        Calendar.getInstance().let {
            val sdf = SimpleDateFormat(dateFormat, Locale.US)
            currentDate = sdf.parse(getDateText(it.time) ?: "")
        }


    }

    private fun initCurrentDate() {
        myCalender = Calendar.getInstance()
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val month = myCalender.get(Calendar.MONTH)
        val year = myCalender.get(Calendar.YEAR)
        onDateSet(null, year, month, day)
    }

    private fun openDatePickerDialog() {

        Locale.setDefault(Locale.ENGLISH)

        KeyboardUtils.hideKeyboardInDialogFragment(this)

        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val month = myCalender.get(Calendar.MONTH)
        val year = myCalender.get(Calendar.YEAR)

        datePickerDialog = DatePickerDialog(
            context,
            this,
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    override fun isValid(): Boolean {
        return formField.isValid()
    }

    override fun getValidationMessage(): String? {
        return return formField.getValidationMessage()
    }

    override fun setValue(text: String?) {
        try {

            //Parse value to date
            val sdf = SimpleDateFormat(dateFormat, Locale.US)
            dateTimestamp = sdf.parse(text!!)
            myCalender.time = dateTimestamp!!

            dateText = getDateText()
            displayDatText = getDisplayDateText()

            setText(displayDatText)
        } catch (ex: Exception) {
            ex.printStackTrace()

            dateText = null
            displayDatText = null

            setText(displayDatText)

        }

    }

    private fun getDateText(): String? {
        return getDateText(dateTimestamp)
    }

    private fun getDateText(date: Date?): String? {
        if (date != null) {
            //dateFormat
            var sdf = SimpleDateFormat(dateFormat, Locale.US)
            return sdf.format(date!!)
        }

        return null
    }

    fun getDisplayDateText(): String? {
        if (dateTimestamp != null) {
            //dateFormat
            val sdf = SimpleDateFormat(displayDateFormat, Locale(Locale.getDefault().language))
            return sdf.format(dateTimestamp!!)
        }

        return null
    }

    override fun getValue(): String? {
        return dateText
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        formField.setAttrChange(attrChange)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        myCalender.set(year, month, dayOfMonth)

        dateTimestamp = myCalender.time

        dateText = getDateText()

        setValue(dateText)

        formField.attrChangeListener?.onChange()

    }


}