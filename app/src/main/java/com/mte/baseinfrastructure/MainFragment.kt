package com.mte.baseinfrastructure

import android.os.Bundle
import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.infrastructurebase.base.BaseFragment

class MainFragment : BaseFragment<ActivityMainBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {
        showConfirmMessagDialog(
            msg = "Confirm msg , Do you want to open error msg?"
//            , positiveBtn =  "confirm"
//            , negativeBtn = "No"
            , negativeBtnHandler = {}
            , positiveBtnHandler = {
                showErrorMsgDialog(
                    msg = "Error message dialog , Do you want to show success Message",
                    positiveBtn = "show success message",
                    positiveBtnHandler = { showSuccessMsgDialog("Success Message , to show info message" , positiveBtnHandler = {showToast("show toast")}) }
//                    , negativeBtn = "No"
                )
            })
    }


}