package com.mte.baseinfrastructure

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.infrastructurebase.base.base_activity.BaseActivity
import com.mte.infrastructurebase.base_toolbar.BaseToolbarActivity
import com.mte.infrastructurebase.data.source.remote.ApiResponse
import com.mte.infrastructurebase.data.source.remote.NetworkBoundResource

class MainActivity : BaseToolbarActivity<ActivityMainBinding>() {


    override fun createToolbarView(): View? {
        return null
    }

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {


        showErrorMsgDialog("asdfasdfasdf")

       /* showConfirmMessagDialog(
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
            })*/


        /*object : NetworkBoundResource<List<String>, LoginResponse, List<ValidatonError>>() {

            override fun saveCallResult(item: LoginResponse) {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<ApiResponse<LoginResponse>> {
                TODO("Not yet implemented")
            }

        }*/


    }


}
