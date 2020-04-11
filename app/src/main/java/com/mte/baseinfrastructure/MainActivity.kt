package com.mte.baseinfrastructure

import android.content.Intent
import android.os.Bundle
import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.baseinfrastructure.demo.DemoViewModel
import com.mte.infrastructurebase.base.base_activity.BaseActivity
import com.mte.infrastructurebase.forms.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.ValidationFieldViewHandler
import com.mte.infrastructurebase.forms.validationRules.Required
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val viewmodel : DemoViewModel by viewModel()

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {
        initForm()
    }



    private fun initForm() {



        binding.nameEt.rules = listOf(Required("adsfasdf"))



        binding.submitBtn.setOnClickListener {

            onSubmitClick()
        }




    }

    private fun onSubmitClick() {
        binding.form.validate(object  : ValidationFieldViewHandler{
            override fun onSuccess() {
            }

            override fun onFailure(fields: ArrayList<IFormControl?>) {

                fields?.forEach{
                    showErrorMsgDialog(it?.getFiledView()?.getValidationMessage())
                }
            }

        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}
