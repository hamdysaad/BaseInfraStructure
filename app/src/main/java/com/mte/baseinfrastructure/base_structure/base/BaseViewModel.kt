package com.mte.baseinfrastructure.base_structure.base

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel(){

    var activity: Activity? = null
    var context: Context? = null

    var fragment : BaseFragment<*>? = null


}
