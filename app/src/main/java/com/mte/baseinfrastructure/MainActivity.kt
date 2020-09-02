package com.mte.baseinfrastructure

import android.os.Bundle
import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.infrastructurebase.base.base_activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {


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
