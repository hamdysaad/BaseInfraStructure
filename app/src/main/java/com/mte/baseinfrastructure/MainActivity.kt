package com.mte.baseinfrastructure

import android.os.Bundle
import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.infrastructurebase.base.base_activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {


        showConfirmMessagDialog(
            msg = "Confirm msg , Do you want to open error msg?"
//            , positiveBtn =  "confirm"
//            , negativeBtn = "No"
            , negativeBtnHandler = {
                binding?.mainContainer?.id?.let { replaceFragment(MainFragment() , it, false) }
            }
            , positiveBtnHandler = {
                showErrorMsgDialog(
                    msg = "Error message dialog , Do you want to show success Message",
                    positiveBtn = "show success message",
                    positiveBtnHandler = { showSuccessMsgDialog("Success Message , to show info message" , positiveBtnHandler = {showInfoMsgDialog("info messsage")}) }
//                    , negativeBtn = "No"
                )
            })


        /*object : NetworkBoundResource<List<String>, LoginResponse, List<ValidatonError>>() {
            override fun createErrorBodyResult(errorBody: String?): List<ValidatonError>? {
                TODO("Not yet implemented")
            }

            override fun getErrorBody(errorBody: String?): String? {
                TODO("Not yet implemented")
            }

            override fun saveCallResult(item: LoginResponse) {
                TODO("Not yet implemented")
            }

            override fun getResult(): List<String>? {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<ApiResponse<LoginResponse>> {
                TODO("Not yet implemented")
            }

        }*/


    }


}
