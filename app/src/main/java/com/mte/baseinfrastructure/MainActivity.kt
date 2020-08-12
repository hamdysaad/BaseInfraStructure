package com.mte.baseinfrastructure

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.hamdy.infrastructurebase.utils.UtilsImageLoader
import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.baseinfrastructure.demo.DemoViewModel
import com.mte.infrastructurebase.base.base_activity.BaseActivity
import com.mte.infrastructurebase.custom_form.FormValidation
import com.mte.infrastructurebase.custom_form.FormValidationError
import com.mte.infrastructurebase.custom_form.FormValidationHandler
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.form_view.interfaces.ValidationFieldViewHandler
import com.mte.infrastructurebase.form_view.validationRules.Required
import com.mte.infrastructurebase.interfaces.OnLocationSuccessListener
import com.mte.infrastructurebase.utils.FusedLocation
import com.mte.infrastructurebase.utils.LocaleHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {


        changeLangAR.setOnClickListener {
            LocaleHelper.setLocale(this , "ar")
            startActivity(Intent(this  , MainActivity::class.java))
            finish()
        }

        changeLangEN.setOnClickListener {
            LocaleHelper.setLocale(this , "en")
            startActivity(Intent(this  , MainActivity::class.java))
            finish()
        }
    }

    override val okRes: Int
        get() = R.string.ok
    override val yesRes: Int
        get() = R.string.ok
    override val noRes: Int
        get() = R.string.ok
}
