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
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val viewmodel : DemoViewModel by viewModel()

    private var fusedLocation: FusedLocation? = null

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {

        viewmodel.getList()

        initForm()

        getLocation()

        ovserveLV()

    }

    private fun ovserveLV() {
        viewmodel.jobsDataLD.observe(this , Observer {

        })
    }


    private fun getLocation() {

        fusedLocation = FusedLocation(this)

        fusedLocation?.getLocation(object  : OnLocationSuccessListener {
            override fun onLocationSuccess(location: Location?) {
                Log.e("llllllllll" , "location ${location.toString()}")
            }
        })
    }

    private fun initForm() {

        UtilsImageLoader.loadImageUrl(
            binding.profileImage ,
            null,
            ContextCompat.getDrawable(this , R.drawable.ic_launcher_background) ,
            ContextCompat.getDrawable(this, R.drawable.ic_launcher_background) ,
            true
        )

        binding.segmentedBtn.rules =
            listOf(Required("gender_required_message"))

        binding.fileAttach.activity = this
        binding.profileImage.activity = this
        binding.organizationNameET.rules = listOf(Required("asdfasdf"))

        binding.submitBtn.setOnClickListener {


            /*FormValidation()
                .addField("", R.id.bottom , listOf(Required(getString(R.string.attachments))))
                .addField("dfg", R.id.bottom , listOf(Required(getString(R.string.attachments))))
                .addField("", R.id.bottom , listOf(Required(getString(R.string.errorMessage))))
                .addField("sdg", R.id.bottom , listOf(Required(getString(R.string.attachments))))
                .validate(object : FormValidationHandler{
                    override fun onSuccess() {
                    }

                    override fun onFailure(fields: ArrayList<FormValidationError?>) {
                        TODO("Not yet implemented")
                    }

                })*/

            binding.form.validate(object : ValidationFieldViewHandler{
                override fun onSuccess() {
//                TODO("Not yet implemented")
                }

                override fun onFailure(fields: ArrayList<IFormControl?>) {
                    fields?.forEach {



                        it?.getValidationView()?.showValidateError()
                        val field = it?.getFiledView()
                        if(field is EditText)
                            field.requestFocus()
                    }
                }

            })
        }



    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fusedLocation?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        binding.fileAttach.onRequestPermissionsResult(requestCode, permissions, grantResults)
        binding.profileImage.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.fileAttach.onActivityResult(requestCode, resultCode, data)
        binding.profileImage.onActivityResult(requestCode, resultCode, data)
    }
}
